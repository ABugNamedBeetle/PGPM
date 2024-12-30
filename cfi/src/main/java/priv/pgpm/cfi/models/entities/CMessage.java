package priv.pgpm.cfi.models.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import priv.pgpm.cfi.models.core.InstructionMessage;
import priv.pgpm.cfi.models.entities.base.BaseInstructionMessage;

@Entity(name="CMESSAGE_REGISTER")
@Data
@EqualsAndHashCode(callSuper=false)
public class CMessage extends BaseInstructionMessage { //clearning message

    private int requestId;  // external

    //genrated internally
    private int cMessageId;

    // private int instructionId;  -> external

    // private Long amount;

    // private String originatorReserveAccount;

    // private String benificiaryReserveAccount;
    
    // private String destinationVirtualAccount;

    // private InstructionMessage.PurposeCode purposeCode;

    
}

