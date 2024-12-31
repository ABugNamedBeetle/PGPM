package priv.pgpm.cfi.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import lombok.val;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import priv.pgpm.cfi.models.requests.InstructionRequest;
import priv.pgpm.cfi.validators.annotation.ValidInstructionRequest;

public class InstructionRequestValidator implements ConstraintValidator<ValidInstructionRequest, InstructionRequest> {

    @Override
    public boolean isValid(InstructionRequest value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        boolean isValid = true;

        if (value.getRequestId() == null || value.getRequestId().length() < 10) {
            context.buildConstraintViolationWithTemplate("Violated RequestId MIN character length of 10")
                    .addPropertyNode("requestId")
                    .addConstraintViolation();
            isValid = false;
        }

        if (value.getMessages().isEmpty()) {
            context.buildConstraintViolationWithTemplate("Violated Instruction Message list length with 0")
                    .addPropertyNode(value.getRequestId())
                    .addPropertyNode("messages")
                    .addConstraintViolation();
        } else {
            value.getMessages().forEach(instruction -> {
                if (!(instruction.getInstructionId() > 0)) {
                    context.buildConstraintViolationWithTemplate("Violated Instruction Message validity")
                            .addPropertyNode(value.getRequestId())
                            .addPropertyNode(instruction.getInstructionId().toString())
                            .addConstraintViolation();
                }
                if (!(instruction.getAmount() > 0)) {
                    context.buildConstraintViolationWithTemplate(
                            "Violated Instruction Message Amount :: " + instruction.getAmount())
                            .addPropertyNode(value.getRequestId())
                            .addPropertyNode(instruction.getAmount().toString())
                            .addConstraintViolation();
                }
                if()
            });
        }

        return isValid;
    }

}
