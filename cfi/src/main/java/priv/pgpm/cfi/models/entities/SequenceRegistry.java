package priv.pgpm.cfi.models.entities;

import org.springframework.cglib.beans.BeanCopier.Generator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class SequenceRegistry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String correlation;
    private Long value;
 
}
