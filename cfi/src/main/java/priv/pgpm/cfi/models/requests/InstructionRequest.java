package priv.pgpm.cfi.models.requests;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import priv.pgpm.cfi.models.core.InstructionMessage;
import priv.pgpm.cfi.models.core.InstructionMessage.PurposeCode;
import priv.pgpm.cfi.validators.annotation.ValidInstructionRequest;


@Data
@ValidInstructionRequest
public class InstructionRequest {

    private String requestId;
    private List<InstructionMessageDTO> messages = new ArrayList<>();

    @JsonIgnore
    public List<InstructionMessage> extractMessages(){
        return messages.stream().map((i)->{
            InstructionMessage im =  new InstructionMessage();
            im.setInstructionId(i.getInstructionId());
            im.setAmount(i.getAmount());
            im.setOriginatorFSC(i.getOriginatorFSC());
            im.setBenificiaryFSC(i.getBenificiaryFSC());
            im.setDestinationVirtualAccount(i.getDestinationVirtualAccount());
            im.setCurrencyCode(Currency.getInstance(i.getCurrencyCode()));
            im.setPurposeCode(InstructionMessage.PurposeCode.valueOf(i.getPurposeCode()));
            return im;
        }).toList();
    }

    @Data
    public static final class InstructionMessageDTO{
        private Long instructionId;
        private Long amount;
        private String currencyCode;
        private String originatorFSC;
        private String benificiaryFSC;
        private String destinationVirtualAccount;
        private String purposeCode;
    }




}
