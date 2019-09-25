package Demo.utils;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        HttpSession session = request.getSession();
        if ( session.getAttribute("LOGIN_USER") != null ){
            return true;
        }else {
            request.getRequestDispatcher("/error/noLogin").forward(request,response);
            return false;
        }
    }

    }