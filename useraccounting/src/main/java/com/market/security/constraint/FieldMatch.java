package com.market.security.constraint;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * This is a class-level annotation where we can compare two fields for equality and pass in an
 * optional message to display to the user if the constraint validation fails. 
 * 
 * @author Johannes Weiss
 *
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = FieldMatchValidator.class)
@Documented
public @interface FieldMatch {
  
  /**
   * Message.
   * @return a message
   */
  String message() default "The fields must match";

  /**
   * 
   * @return
   */
  Class<?>[] groups() default {};

  /**
   * 
   * @return
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * Firs field.
   * @return
   */
  String first();

  /**
   * Second field.
   * @return
   */
  String second();

  @Target({ TYPE, ANNOTATION_TYPE })
  @Retention(RUNTIME)
  @Documented
  @interface List {
    FieldMatch[] value();
  }
}
