package priv.pgpm.cfi.models.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.factory.spi.CustomIdGeneratorCreationContext;

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

@Entity(name = "CMESSAGE_REGISTER")
@Data
@EqualsAndHashCode(callSuper = false)
public class CMessage extends BaseInstructionMessage { // clearning message

    // external

    /**
     * genrated internally for internal refrence,as external system can send any
     * refrence even dupplicate
     */
    @Id
    @Column(name = "Id")
    @CINSequenceID(name = "sds", incrementBy = 1)
    // @GeneratedValue(generator = "custom-order-id")
    // @GenericGenerator(
    //     name = "custom-order-id",
    //     strategy = "priv.pgpm.cfi.models.entities.base.CINSequenceGenerator"
    // )
    private String cInstructionId;
    private String cRequestId;

    

}
