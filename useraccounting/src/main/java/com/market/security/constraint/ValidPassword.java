package com.market.security.constraint;

import java.lang.annotation.Documented;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)

public @interface ValidPassword {
 
    String message() default "Invalid Password";
 
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
 
}