package com.revature.aspects;

import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
// This class is an ASPECT - a class that can trigger functionality at any point in our app
// when a method is called, this class can listen for that and trigger some method invocation
// made possible with Spring AOP library
@Component
public class AuthAspect {

    // check for login for certain methods
    // check for role for certain methods

    // This @Before checks for login BEFORE any controller method is called, EXCEPT for login/register
    // the asterisk before com.revature.controllers means any return type
    // the (..) after login means any number of parameters to account for method overloading
    // the * after controllers means any class in the controllers package
    // Order annotation is used to specify the order of execution of the aspect
    @Order(1)
    @Before("within(com.revature.controllers.*) " +
            "&& !execution(* com.revature.controllers.AuthController.login(..))" +
            "&& !execution(* com.revature.controllers.UserController.insertUser(..))")
    public void checkLoggedIn(){
        // get the session
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        //getSession(false) gets the session, but doesn't create one if it doesn't exist, returns null instead
        //getSession(true) on the other hand, gets the session, and creates one if it doesn't exist
        // btw, session management is all done automatically by the server
        HttpSession session = attr.getRequest().getSession(false);
        System.out.println("Checking login..." + session.getAttribute("userID"));
        if (session == null || session.getAttribute("userID") == null){
            throw new IllegalArgumentException("You must be logged in to access this functionality");
        }
    }

    // This advice will check if user is a manager before allowing access to any method with @AdminOnly annotation
    @Order(2)
    @Before("@annotation(AdminOnly)")
    public void checkAdmin(){
        if(!"Manager".equals(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getSession().getAttribute("role"))){

            throw new IllegalArgumentException("User is not a manager!");

        }

        /*
        Above code same as:

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();

        if (session.getAttribute("role") != "Manager"){
            throw new IllegalArgumentException("You must be a manager to access this functionality");
        }
        */

    }


}
