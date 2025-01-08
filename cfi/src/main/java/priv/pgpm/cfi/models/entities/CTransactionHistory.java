package priv.pgpm.cfi.models.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

public class CTransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "c_his_seq")
    @SequenceGenerator(name = "c_his_seq", initialValue = 1000, allocationSize = 50 )
    @ValueG
    private Long historyId;

    private String cInstructionId;



}
