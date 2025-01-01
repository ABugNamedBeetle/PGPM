package priv.pgpm.cfi.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Currency;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import lombok.val;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import priv.pgpm.cfi.models.core.InstructionMessage;
import priv.pgpm.cfi.models.requests.InstructionRequest;
import priv.pgpm.cfi.validators.annotation.ValidInstructionRequest;

public class InstructionRequestValidator implements ConstraintValidator<ValidInstructionRequest, InstructionRequest> {

    @Override
    public boolean isValid(InstructionRequest value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        System.out.println("validated");
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
                    .addConstraintViolation();
            isValid = false;
        } else {
            int index = 0;
            for (InstructionRequest.InstructionMessageDTO instruction : value.getMessages()) {
                if (instruction.getInstructionId() <= 0) {
                    addInstrucitonViolation("messages", "instructionId", index, "Violated Instruction ID validity",
                            context);
                    isValid = false;
                }

                if (instruction.getAmount() <= 0) {
                    String msg = "Violated Amount with illegal value :: " + instruction.getAmount();
                    addInstrucitonViolation("messages", "amount", index, msg, context);
                    isValid = false;
                }

                // Validate Currency Code
                try {
                    Currency.getInstance(instruction.getCurrencyCode());
                } catch (Exception e) {
                    String msg = "Violated Currency Code with illegal value :: " + instruction.getCurrencyCode();
                    addInstrucitonViolation("messages", "currencyCode", index, msg, context);
                    isValid = false;
                }

                // Validate FSCs
                String v = instruction.getOriginatorFSC();
                if (v == null || v.trim().length() < 10 || v.trim().length() > 21) {
                    v = (v == null) ? "" : v;
                    String msg = String.format("Invalid Originator FSC: %s (%d)", v, v != null ? v.length() : 0);
                    addInstrucitonViolation("messages", "originatorFSC", index, msg, context);
                    isValid = false;
                }

                v = instruction.getBenificiaryFSC();
                if (v == null || v.trim().length() < 10 || v.trim().length() > 21) {
                    v = (v == null) ? "" : v;
                    String msg = String.format("Violated Beneficiary FSC :: %s (%d)", v, v.length());
                    addInstrucitonViolation("messages", "benificiaryFSC", index, msg, context);
                    isValid = false;
                }
                v = instruction.getDestinationVirtualAccount();
                if (v == null || v.trim().length() < 10 || v.trim().length() > 21) {
                    v = (v == null) ? "" : v;
                    String msg = String.format("Violated DestinationVirtualAccount :: %s (%d)", v, v.length());
                    addInstrucitonViolation("messages", "destinationVirtualAccount", index, msg, context);
                    isValid = false;
                }
                try {
                    InstructionMessage.PurposeCode.valueOf(instruction.getPurposeCode());
                } catch (Exception e) {
                    String msg = "Violated Purpose Code illegal value :: " + instruction.getPurposeCode();
                    addInstrucitonViolation("messages", "purposeCode", index, msg, context);
                    isValid = false;
                }
            }
        }

        return isValid;
    }

    private void addInstrucitonViolation(String root, String child, int index, String reason,
            ConstraintValidatorContext context) {
        context.buildConstraintViolationWithTemplate(reason)
                .addPropertyNode(root)
                .addPropertyNode(child).inIterable().atIndex(index)
                .addConstraintViolation();
    }

}
