package priv.pgpm.cfi.models.entities;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.generator.EventType;
// import org.hibernate.annotations.Generated;
// import org.hibernate.annotations.GeneratorType;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.SequenceGenerators;

public class CTransactionHistory {

    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "c_his_seq")
    @SequenceGenerator(name = "c_his_seq", initialValue = 1000, allocationSize = 50 )
    private Long historyId;

    
    private String cInstructionId;



}
