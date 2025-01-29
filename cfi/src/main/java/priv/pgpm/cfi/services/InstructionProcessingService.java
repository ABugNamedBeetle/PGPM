package priv.pgpm.cfi.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import priv.pgpm.cfi.models.core.InstructionMessage;
import priv.pgpm.cfi.models.entities.CMessage;
import priv.pgpm.cfi.models.requests.InstructionRequest;
import priv.pgpm.cfi.models.responses.CMessageRepository;
import priv.pgpm.cfi.models.responses.InstructionResponse;


@Service
public class InstructionProcessingService {

    private CMessageRepository cMessageRepository;

    public  InstructionProcessingService(CMessageRepository cMessageRepository){
        this.cMessageRepository = cMessageRepository;
    }

    public InstructionResponse.Accepted acceptInstructionRequest(String requestId, List<InstructionMessage> messages) {
        CMessage c = new CMessage();
        c.setIncomingRequestId(requestId);
        c.setIncomingInstructionId(messages.get(0).getInstructionId());
        c.setAmount(messages.get(0).getAmount());
        c.setBenificiaryFSC(messages.get(0).getBenificiaryFSC());
        c.setCurrencyCode(messages.get(0).getCurrencyCode());
        c.setPurposeCode(messages.get(0).getPurposeCode());
        c.setDestinationVirtualAccount(messages.get(0).getDestinationVirtualAccount());
        c.setOriginatorFSC(messages.get(0).getOriginatorFSC());
        cMessageRepository.save(c);
        InstructionResponse.Accepted resp = new InstructionResponse.Accepted(messages.size(), requestId);
        return resp;
    }

     
}
