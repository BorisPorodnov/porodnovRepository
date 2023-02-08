package net.porodnov.bank.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Аспект, который будет выводить в лог время выполнения
 */
@Aspect
@Component
public class LogExecutionTimeAspect {

    private static final Logger log = LoggerFactory.getLogger(LogExecutionTimeAspect.class);
    private static final long MIN_WANTED_TIME = 500;

    @Pointcut("@annotation(net.porodnov.bank.aspect.LogExecutionTime)")
    private void annotatedMethodOnly() {
    }

    @Pointcut("@within(net.porodnov.bank.aspect.LogExecutionTime)")
    private void annotatedClassPublicMethods() {
    }

    @Around("annotatedMethodOnly() || annotatedClassPublicMethods()")
    public Object logExecutionTimeAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - startTime;
        if (executionTime >= MIN_WANTED_TIME) {
            log.info("Время выполнения метода {} заняло {} мс.", joinPoint.getSignature(), executionTime);
        }
        return result;
    }
}
