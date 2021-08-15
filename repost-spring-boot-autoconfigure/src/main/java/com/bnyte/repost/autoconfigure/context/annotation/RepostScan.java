package com.bnyte.repost.autoconfigure.context.annotation;

import com.bnyte.repost.autoconfigure.scanner.RepostScanRegister;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(RepostScanRegister.class)
public @interface RepostScan {

    String[] basePackages() default {};

    String[] value() default {};

    Class<?>[] basePackageClasses() default {};
}
