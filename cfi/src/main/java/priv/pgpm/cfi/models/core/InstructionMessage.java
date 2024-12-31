package priv.pgpm.cfi.models.core;

import java.util.Currency;
import java.util.Objects;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import priv.pgpm.cfi.exceptions.InstructionInputException;

@Data
public class InstructionMessage{
    // @NotNull(message = "Instruction ID cannot be null")
    protected Long instructionId;
    // @NotNull(message = "Amount is required")
    protected Long amount;
    
    // @NotNull(message = "Currency Code cannot be empty")
    @Setter(value = AccessLevel.NONE)
    protected Currency currencyCode;
 
    @JsonSetter(value = "currencyCode")
    public void setCurrencyCode(String code) throws InstructionInputException {
        try{
            this.currencyCode = Currency.getInstance(code);
        }catch(Exception e){
            throw new InstructionInputException(code+ " Currency Code is not valid");
        }
    }

    @JsonGetter(value = "currencyCode")
    public String getCurrencyAsCode(){
        return this.currencyCode.getDisplayName();
    }

    // @NotNull(message = "Originator Financial System Code cannot be empty")
    // @Size(min = 10, max = 20, message = "Originator Financial System Code  must be between 10 and 20 characters")
    protected String originatorFSC;

    // @NotNull(message = "Beneficiary Financial System Code  cannot be empty")
    // @Size(min = 10, max = 20, message = "Beneficiary Financial System Code must be between 10 and 20 characters")
    protected String benificiaryFSC;
    
    // @NotNull(message = "Destination Virtual Account cannot be empty")
    // @Size(min = 10, max = 20, message = "Destination Virtual Account must be between 10 and 20 characters")
    protected String destinationVirtualAccount;

    // @NotNull(message="Purpose Code cannot be empty")
    @Setter(value = AccessLevel.NONE)
    protected InstructionMessage.PurposeCode purposeCode;

    public void setPurposeCode(String purpose) throws InstructionInputException {
        try{
            this.purposeCode = PurposeCode.valueOf(purpose);
        }catch(Exception e){
            throw new InstructionInputException(purpose+ " Purpose Code is not valid");
        }
    }


    public static enum PurposeCode{
        CLEARANCE,
        CASH_SETTELMENT
    }
}
