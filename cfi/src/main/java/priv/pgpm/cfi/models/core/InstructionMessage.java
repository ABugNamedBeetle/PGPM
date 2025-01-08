package priv.pgpm.cfi.models.core;

import java.util.Currency;
import java.util.Objects;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.val;
import priv.pgpm.cfi.exceptions.InstructionInputException;

@Data
public class InstructionMessage{
    protected Long instructionId;
    protected Long amount;
    
    protected Currency currencyCode;
 
    protected String originatorFSC;

    protected String benificiaryFSC;
    
    protected String destinationVirtualAccount;

   
    protected InstructionMessage.PurposeCode purposeCode;

    
    public static enum PurposeCode{
        CLEARANCE,
        CASH_SETTELMENT


    }
}
