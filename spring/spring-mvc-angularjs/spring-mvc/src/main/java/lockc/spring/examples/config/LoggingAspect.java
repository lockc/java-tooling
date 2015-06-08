package lockc.spring.examples.config;

import lockc.spring.examples.controller.ExampleController;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect
{
    private final static Logger USER_LOGGER = LoggerFactory.getLogger(ExampleController.class);

    private final static String BASE_PACKAGE = "lockc.spring.examples";

    @Before("execution(* " + BASE_PACKAGE + ".controller.ExampleController.*(..))")
    public void usersResourceBeforeAdvice(JoinPoint joinPoint)
    {
        USER_LOGGER.trace("Entering method : " + joinPoint.getSignature().toShortString());
    }

    @After("execution(* " + BASE_PACKAGE + ".controller.ExampleController.*(..))")
    public void usersResourceAfterAdvice(JoinPoint joinPoint)
    {
        USER_LOGGER.trace("Exiting method : " + joinPoint.getSignature().toShortString());
    }
}
