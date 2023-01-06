package net.porodnov.bank.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для указания того, что время работы метода класса должно быть выведено в лог
 * Ecли указана над классом, то в лог попадет время работы всех публичных методом.
 * Eсли указана над методом, то в лог попадет время работы этого метода, при условии, что он публичный
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface LogExecutionTime {
}
