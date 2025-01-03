package priv.pgpm.cfi.models.entities.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hibernate.annotations.IdGeneratorType;
import org.hibernate.annotations.ValueGenerationType;
import org.hibernate.id.enhanced.Optimizer;

@IdGeneratorType(CINSequenceGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD})
public @interface CINSequenceID {
    String name();
	int startWith() default 1;
	int incrementBy() default 50;
	// Class<? extends Optimizer> optimizer() default Optimizer.class;
}