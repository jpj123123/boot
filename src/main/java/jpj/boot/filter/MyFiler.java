package jpj.boot.filter;

import jpj.boot.application.ApplicatoinConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: jingpj
 * @Date：creste in 2018/2/11
 */
@Component
@WebFilter(urlPatterns = "/*")
@Order(1)
@Slf4j
public class MyFiler implements Filter {
    @Resource
    private ApplicatoinConfig config;
    //private List<String> ignoreLoginList = config.getListLoginUrl();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
//        req.setCharacterEncoding("UTF-8");
//        res.setCharacterEncoding("UTF-8");
        //String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        //boolean allowedPath = ALLOWED_PATHS.contains(path);
        //System.out.println(request.getRequestURI());
        String path_ = request.getContextPath();
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI();
        boolean b = url.matches("^https?://.+(\\.ico|\\.jpg|\\.png|\\.js|\\.css|\\.gif|\\.aspx)$");

        if (!b) {
            //log.info(url);
        } else {//静态文件忽略验证
            chain.doFilter(req, res);
            return;
        }
        //开发先注释掉
        if (!config.getIgnoreLoginUrlList().contains(request.getRequestURI())) {
            //获取登录姓名
            String name = (String) request.getSession().getAttribute(request.getSession().getId());
            Integer id = (Integer) request.getSession().getAttribute(name);
            if (StringUtils.isEmpty(name) || id == null || id == 0) {
                response.sendRedirect("/login");
                return;
            }
            log.info("userName:" + name + "\turl:" + request.getRequestURI());
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}
