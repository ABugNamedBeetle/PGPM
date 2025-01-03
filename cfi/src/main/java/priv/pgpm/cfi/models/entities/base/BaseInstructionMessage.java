package priv.pgpm.cfi.models.entities.base;

import java.time.LocalDateTime;
import java.util.Currency;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import priv.pgpm.cfi.models.core.InstructionMessage;

/** Message containing data for settelment */
@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class BaseInstructionMessage {

    // @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private int uuid;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    private String refRequestId;
    protected Long refInstructionId;
    protected Long amount;
    protected Currency currencyCode;
    protected String originatorFSC;
    @jakarta.annotation.Nullable
    protected String benificiaryFSC;
    @jakarta.annotation.Nullable
    protected String destinationVirtualAccount;
    protected InstructionMessage.PurposeCode purposeCode;

}
