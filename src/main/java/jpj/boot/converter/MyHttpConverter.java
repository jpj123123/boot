package jpj.boot.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import jpj.boot.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;

import static com.alibaba.fastjson.serializer.SerializerFeature.*;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/1
 */
@Slf4j
public class MyHttpConverter extends AbstractHttpMessageConverter<Object> {
    private static SerializerFeature[] serializerFeatures =
            {

                    WriteDateUseDateFormat, DisableCircularReferenceDetect};
    private static final Charset defaultCharset = Charset.forName("UTF-8");
//    {QuoteFieldNames, WriteMapNullValue, WriteNullStringAsEmpty,
//            WriteNullBooleanAsFalse, WriteNullListAsEmpty, WriteNullNumberAsZero,
//            WriteDateUseDateFormat, DisableCircularReferenceDetect};

    public MyHttpConverter() {
        super(new MediaType("application", "json", Charset.forName("UTF-8")),
                new MediaType("application", "*+json", Charset.forName("UTF-8")));
        //super(defaultCharset, new MediaType[]{MediaType.APPLICATION_JSON_UTF8, MediaType.ALL});
    }

    @Override
    protected boolean supports(Class aClass) {
        return true;
    }

    @Override
    protected Object readInternal(Class clazz, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        String jsonBody = IOUtils.toString(httpInputMessage.getBody(), "UTF-8");
        return JSON.parseObject(jsonBody, clazz);
    }

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        if(obj instanceof Result){
            httpOutputMessage.getBody().write(JSON.toJSONString(obj, serializerFeatures).getBytes("UTF8"));
            return;
        }
        Result jsonObject = new Result();
        jsonObject.setMessage("success");
        jsonObject.setCode("0");
        //String jsonStr = JSON.toJSONString(obj, serializerFeatures);
        jsonObject.setBody(obj);
        httpOutputMessage.getBody().write(JSON.toJSONString(jsonObject, serializerFeatures).getBytes("UTF8"));
    }

    public static void main(String[] args) {
        User user = new User();
        user.setName("admin");
        user.setCreateTime(new Date());

        System.out.println(JSON.toJSONString("asdq中国",serializerFeatures));
        User u = JSON.parseObject(JSON.toJSONString(user, serializerFeatures),User.class);
        System.out.println(u.getCreateTime().getTime());
    }
}
