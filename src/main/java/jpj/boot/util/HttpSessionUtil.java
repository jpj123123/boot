package jpj.boot.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpSession;

/**
 * @Author: jingpj
 * @Date：creste in 2018/3/2
 */
public class HttpSessionUtil {
    /**
     * 清除登陆信息
     */
    public static void clearLoginSession(HttpSession session){
        if(session == null){
            return;
        }
        //清除session
        String sessionId  = session.getId();
        String userName = (String)session.getAttribute(sessionId);
        if(StringUtils.isNotBlank(userName)){
            session.removeAttribute(userName);
            session.removeAttribute(sessionId);
        }
    }
    public static Integer getUserId(HttpSession session){
        if(session == null){
            return 0;
        }
        //清除session
        String sessionId  = session.getId();
        String userName = (String)session.getAttribute(sessionId);
        if(StringUtils.isNotBlank(userName)){
            return (Integer)session.getAttribute(userName);
        }
        return 0;
    }
    /**
     * 清除登陆信息
     */
    public static void clearLoginSessionByUserName(HttpSession session, String userName){
        if(session == null){
            return;
        }
        if(StringUtils.isNotBlank(userName)){
            session.removeAttribute(userName);
        }
    }
}
