package priv.pgpm.cfi.models.entities.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hibernate.annotations.ValueGenerationType;

@ValueGenerationType(generatedBy = SeqValGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD})
public @interface SequenceValueID {
    String prefix();
    String correlation();
	int buffer() default 10;
}
