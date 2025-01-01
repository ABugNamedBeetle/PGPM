package priv.pgpm.cfi.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import priv.pgpm.cfi.models.core.InstructionMessage;
import priv.pgpm.cfi.models.requests.InstructionRequest;
import priv.pgpm.cfi.models.responses.InstructionResponse;


@Service
public class InstructionProcessingService {


    public InstructionResponse.Accepted acceptInstructionRequest(String requestId, List<InstructionMessage> messages) {
        InstructionResponse.Accepted resp = new InstructionResponse.Accepted(messages.size(), requestId);
        return resp;
    }

     
}
