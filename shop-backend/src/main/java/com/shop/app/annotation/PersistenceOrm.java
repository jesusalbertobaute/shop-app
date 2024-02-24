package com.shop.app.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

@Target({ TYPE })
@Retention(RUNTIME)
@Documented
@Component
public @interface PersistenceOrm {
	
	@AliasFor(annotation= Component.class)
	String value() default "";

}
