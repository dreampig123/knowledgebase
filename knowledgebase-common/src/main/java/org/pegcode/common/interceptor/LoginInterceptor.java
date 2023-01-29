package org.pegcode.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    /*private static final Pattern SHOULD_NOT_FILTER_URL_PATTERN;

    static {
        List<String> urlList = new ArrayList<>();
        // 将不走拦截器的请求存放到Pattern
        urlList.add("(socket/.*)");
        urlList.add("(user/findUserList)");
        StringBuilder sb = new StringBuilder();
        for (String url : urlList) {
            sb.append(url);
            sb.append("|");
        }
        sb.setLength(sb.length() - 1);
        SHOULD_NOT_FILTER_URL_PATTERN = Pattern.compile(sb.toString());
    }*/

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        // 获取访问的url
        //String servletPath = request.getServletPath();
        // 排除特定请求
        /*if (SHOULD_NOT_FILTER_URL_PATTERN.matcher(servletPath).find()) {
            return true;
        }*/
        if (session.getAttribute("user") != null) {
            // 可能有的项目在校验完session，还会校验token
            String token = request.getHeader("accessToken");
            // 此处业务
            if(StringUtils.isNotEmpty(token)){
                return true;
            }
        }
        log.error("当前用户没有访问权限");
        response.setStatus(401);
        return false;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
