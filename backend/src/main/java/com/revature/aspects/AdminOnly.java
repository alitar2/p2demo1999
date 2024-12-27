package com.revature.aspects;


// custom annotation to set a controller method to only be accessible by admins by annotating the method

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // this annotation will only be used on methods
@Retention(RetentionPolicy.RUNTIME) // this annotation will be available at runtime
public @interface AdminOnly {

    // no fields or methods needed in this annotation

}
