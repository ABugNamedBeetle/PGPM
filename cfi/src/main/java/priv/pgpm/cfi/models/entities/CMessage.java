package priv.pgpm.cfi.models.entities;

import org.hibernate.annotations.GenericGenerator;


import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import priv.pgpm.cfi.models.core.InstructionMessage;
import priv.pgpm.cfi.models.entities.base.BaseInstructionMessage;
import priv.pgpm.cfi.models.entities.base.CINSequenceID;
import priv.pgpm.cfi.models.entities.base.SequenceValueID;

@Entity(name = "CMESSAGE_REGISTER")
@Data
@EqualsAndHashCode(callSuper = false)
public class CMessage extends BaseInstructionMessage { // clearning message

    
    @Id
    @CINSequenceID(name = "cmessage_seq", incrementBy = 1)
    private String messageId;

    @SequenceValueID(prefix = "CREQ", correlation = "c_request_seq")
    private String requestId;

    
    @Data
    @Entity(name = "cmessage_seq")
    public static class CMessageSequence{
        @Id
        private Integer id;
        private Long value;

    }
}
