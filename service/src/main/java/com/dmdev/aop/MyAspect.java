package com.dmdev.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class MyAspect {

    @Pointcut("within(com.dmdev.service.*Service)")
    public void isServiceLayer() {
    }

    /*
       execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern) throws-pattern?)
    */
    @Pointcut("isServiceLayer() && execution(public * com.dmdev.service.*Service.findAll())")
    public void isFindAllServiceLayer() {
    }

    @Pointcut("isServiceLayer() && args(Integer)")
    public void isMethodWithIdParamServiceLayer() {
    }

    @Pointcut("isServiceLayer() && args(Integer, Object)")
    public void isUpdateServiceLayer() {
    }

    @Pointcut("isServiceLayer() && execution(public * com.dmdev.service.*Service.create(*))")
    public void isCreateServiceLayer() {
    }

    /*
    execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern) throws-pattern?)
 */
    @Pointcut("isServiceLayer() && execution(public org.springframework.security.core.userdetails.UserDetails com.dmdev.service.*Service.loadUserByUsername(String))")
    public void isLoadUserServiceLayer() {
    }

    @Before(value = "isMethodWithIdParamServiceLayer() " +
            "&& args(id) " +
            "&& target(service)")
    public void addLoggingMethodWithId(JoinPoint joinPoint,
                                       Object id,
                                       Object service) {
        log.info("before - invoked method {} in class {}, with id {}", joinPoint.getSignature().getName(), service, id);
    }

    @Before(value = "isUpdateServiceLayer() " +
            "&& args(id, objectDto) " +
            "&& target(service)")
    public void addLoggingForUpdate(JoinPoint joinPoint,
                                    Object id,
                                    Object objectDto,
                                    Object service) {
        log.info("before - invoked method {} in class {}, with id {} and dto {}", joinPoint.getSignature().getName(), service,id, objectDto);
    }

    @Before(value = "isCreateServiceLayer() " +
            "&& args(objectDto) " +
            "&& target(service)")
    public void addLoggingForCreate(JoinPoint joinPoint,
                                    Object objectDto,
                                    Object service) {
        log.info("before - invoked method {} in class {}, with dto {}", joinPoint.getSignature().getName(), service, objectDto);
    }

    @Before(value = "isLoadUserServiceLayer() " +
            "&& args(username) " +
            "&& target(service)")
    public void addLoggingLoadUser(JoinPoint joinPoint,
                                   Object username,
                                   Object service) {
        log.info("before - invoked method {} in class {}, with username {}", joinPoint.getSignature().getName(), service, username);
    }

    @AfterReturning(value = "isServiceLayer() " +
            "&& target(service)",
            returning = "result")
    public void addLoggingAfterReturning(JoinPoint joinPoint, Object result, Object service) {
        log.info("after returning - invoked method {} in class {}, result {}", joinPoint.getSignature().getName(), service,result);
    }

}
