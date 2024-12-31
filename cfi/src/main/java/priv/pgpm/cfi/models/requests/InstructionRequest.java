package priv.pgpm.cfi.models.requests;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import priv.pgpm.cfi.models.core.InstructionMessage;
import priv.pgpm.cfi.validators.annotation.ValidInstructionRequest;


@Data
@ValidInstructionRequest
public class InstructionRequest {

    private String requestId;
    private List<InstructionMessage> messages = new ArrayList<>();
}
