package com.market.security.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;

/**
 * This class reads the two fields and the message during the initialization. The isValid() method
 * is invoked during bean validation. This method reads and compares the values of the two fields
 * using commons-beanutils. When the first field doesn’t match the second field the validation fails
 * and we add the error message to the conflicting property.
 * 
 * @author Johannes Weiss
 *
 */
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

  private String firstFieldName;
  private String secondFieldName;
  private String message;

  @Override
  public void initialize(final FieldMatch constraintAnnotation) {
    firstFieldName = constraintAnnotation.first();
    secondFieldName = constraintAnnotation.second();
    message = constraintAnnotation.message();
  }

  @Override
  public boolean isValid(final Object value, final ConstraintValidatorContext context) {
    boolean valid = false;
    try {
      final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
      final Object secondObj = BeanUtils.getProperty(value, secondFieldName);

      if (firstObj != null && secondObj != null) {
        valid = firstObj.equals(secondObj);
      } else {
        valid = false;
      }
      
    } catch (final Exception ignore) {
      // ignore
    }

    if (!valid) {
      context.buildConstraintViolationWithTemplate(message).addPropertyNode(secondFieldName)
          .addConstraintViolation().disableDefaultConstraintViolation();
    }

    return valid;
  }
}