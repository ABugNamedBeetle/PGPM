package priv.pgpm.cfi.models.entities.base;

import java.lang.reflect.Member;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumSet;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.AnnotationBasedGenerator;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;
import org.hibernate.generator.EventTypeSets;
import org.hibernate.generator.GeneratorCreationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;

public class SeqValGenerator implements BeforeExecutionGenerator, AnnotationBasedGenerator<SequenceValueID> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeqValGenerator.class);
    // @Autowired

    private SequenceValueID config;

    private Long current = 1L;
    private Long limit = 0L;

    // public SeqValGenerator() {
    // // this.config = config;
    // // System.out.println(config);
    // // this.sequenceService = sequenceService;
    // System.out.println(this.sequenceService == null);
    // }

    @Override
    public EnumSet<EventType> getEventTypes() {
        return EventTypeSets.INSERT_ONLY;
    }

    @Override
    public Object generate(SharedSessionContractImplementor session, Object owner, Object currentValue,
            EventType eventType) {

        if (current > limit) {
            current = getNextValue(session, current, config);
            limit = current + this.config.buffer() - 1;
            LOGGER.info("created new limit = {}", limit);
        }

        String val = this.config.prefix() + current;
        current++;
        return val;
    }

    private Long getNextValue(SharedSessionContractImplementor session, Long current, SequenceValueID config) {
        return session.doReturningWork(connection -> {
            
            try (PreparedStatement getStmt = 
            connection.prepareStatement("SELECT value FROM sequence_registry sr where correlation = \'"+config.correlation()+"\' limit 1;")) {
                     
                try (ResultSet rs = getStmt.executeQuery()) {
                    if (rs.next()) {
                        Long currentValue = rs.getLong(1);
                        PreparedStatement updateStatement = connection.prepareStatement("UPDATE sequence_registry SET value=" +(currentValue+config.buffer())+"  WHERE correlation=\'"+config.correlation()+"\';");
                        updateStatement.executeUpdate();
                        return currentValue + 1;
                        
                    } else {
                            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO sequence_registry (correlation, value) VALUES(\'"+config.correlation()+"\', "+config.buffer()+");");
                            insertStatement.executeUpdate();
                            return 1L;
                    }
                }
            }catch (Exception e) {
                LOGGER.error("Failed", e);
                throw new RuntimeException("Fatal exception in generatign sequence value", e);
            }
        });
              
    }

    @Override
    public void initialize(SequenceValueID config, Member member, GeneratorCreationContext context) {
        this.config = config;
        System.out.println(config);
    }

}
