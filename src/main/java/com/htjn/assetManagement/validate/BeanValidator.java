package com.htjn.assetManagement.validate;

import org.springframework.util.Assert;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Bean验证器，使用了Hibernate Validator框架。
 */

public class BeanValidator {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();    //获得验证器

    public static <T> boolean validate(T entity, Class<?> ... groups) {
        //执行验证
        Set<ConstraintViolation<T>> violations = validator.validate(entity, groups);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<T> constraintViolation : violations) {
                String message = constraintViolation.getMessage();
                Assert.state(false, message);
            }
        }
        return true;
    }

}
