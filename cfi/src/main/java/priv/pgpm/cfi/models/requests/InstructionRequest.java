package priv.pgpm.cfi.models.requests;

import java.util.ArrayList;
import java.util.List;

import priv.pgpm.cfi.models.core.InstructionMessage;



public class InstructionRequest {

    private String requestId;
    private List<InstructionMessage> messages = new ArrayList<>();
}
