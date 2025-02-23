package com.rinhaback.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.constraints.NotNull;



@Constraint(validatedBy = com.rinhaback.validation.OnlyWords.TextOnlyValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface OnlyWords {
    String message() default "O campo só pode conter palavras (apenas letras)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    public class TextOnlyValidator implements ConstraintValidator<OnlyWords, String>{

        private static final String WORD_REGEX = "^[a-zA-Z]+$";
    
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (value == null || value.isEmpty()) {
                return false;
            }
            return value.matches(WORD_REGEX);
        }
    }
    
}

