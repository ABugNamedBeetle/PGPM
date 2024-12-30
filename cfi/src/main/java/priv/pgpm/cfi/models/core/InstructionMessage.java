package priv.pgpm.cfi.models.core;

import lombok.Data;
import priv.pgpm.cfi.models.entities.base.BaseInstructionMessage;

@Data
public class InstructionMessage{
    protected int instructionId;

    protected Long amount;

    protected String originatorReserveAccount;

    protected String benificiaryReserveAccount;
    
    protected String destinationVirtualAccount;

    protected InstructionMessage.PurposeCode purposeCode;

    public static enum PurposeCode{
        CLEARANCE,
        CASH_SETTELMENT
    }
}
