package priv.pgpm.cfi.validators.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import priv.pgpm.cfi.validators.InstructionRequestValidator;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = InstructionRequestValidator.class)
public @interface ValidInstructionRequest {
    String message() default "Invalid InstructionRequest";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}