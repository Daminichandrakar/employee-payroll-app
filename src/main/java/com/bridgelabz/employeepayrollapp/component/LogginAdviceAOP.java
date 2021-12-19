package com.bridgelabz.employeepayrollapp.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Purpose : To demonstrate log file in employee payroll application
 *
 * @author : DAMINI CHANDRAKAR
 * @version : 0.0.1-SNAPSHOT
 * @since : 15-12-2021
 */
@Component
@Aspect
@Slf4j
public class LogginAdviceAOP {

    /**
     * Purpose : PointCut to select all the methods available. So advice will be called
     * for all the methods.
     */
    @Pointcut(value = "execution(* com.bridgelabz.employeepayrollapp.*.*.*(..) )")
    public void myPointCut() {
    }

    /**
     * Purpose : This method is created to centralize the logging statement
     *
     * @param point : It manages the reflection of the program for getting the method details and input parameters
     * @return : The JSON format of logger statement before and after the advice
     * @throws Throwable : when there is any exception
     */
    @Around("myPointCut()") //It is applied before and after calling the actual method.
    public Object applicationLogger(ProceedingJoinPoint point) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = point.getSignature().getName();
        String className = point.getTarget().getClass().toString();
        Object[] array = point.getArgs();
        log.info("method invoked" + className + " : " + methodName + "()" + "arguments : "
                + mapper.writeValueAsString(array));
        Object object = point.proceed();
        log.info(className + " : " + methodName + "()" + "Response : "
                + mapper.writeValueAsString(object));
        return object;
    }
}
