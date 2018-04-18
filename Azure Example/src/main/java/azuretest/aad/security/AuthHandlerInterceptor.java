package azuretest.aad.security;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthHandlerInterceptor implements HandlerInterceptor {

    public boolean preHandler(HttpServletRequest request) {

        if (request.getHeader("auth-key").length() == 12){
            return true; // pass this request
        }
        else {
            System.out.println("Auth??");
            return false;
        }
    }
}
