package priv.pgpm.cfi.models.entities.base;

import java.io.Serializable;
import java.lang.reflect.Member;
import java.util.EnumSet;
import java.util.Properties;

import org.hibernate.MappingException;
import org.hibernate.boot.model.relational.SqlStringGenerationContext;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.EventType;
import org.hibernate.generator.GeneratorCreationContext;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.id.factory.spi.CustomIdGeneratorCreationContext;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

public class CINSequenceGenerator implements IdentifierGenerator{


    // public CINSequenceGenerator(CINSequenceID config) {
    //     this.sequenceName = config.name();
    // // this.startWith = config.startWith(); 
    // // this.incrementBy = config.incrementBy();
    // // this.counter = startWith;
    // }

    // private final String sequenceName;
    // private final int startWith;
    // private final int incrementBy;
    // private int counter;


    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        synchronized (this) {
            String id = "sdsd";
            return id;
        }
    }


    @Override
    public void configure(Type type, Properties parameters, ServiceRegistry serviceRegistry) {
        // TODO Auto-generated method stub
        System.out.println(1);
        IdentifierGenerator.super.configure(type, parameters, serviceRegistry);
    }
    
    
    @Override
    public EnumSet<EventType> getEventTypes() {
        System.out.println(2);
        // TODO Auto-generated method stub
        return IdentifierGenerator.super.getEventTypes();
    }
    
    
    @Override
    public void create(GeneratorCreationContext creationContext) throws MappingException {
        System.out.println(3);
        // TODO Auto-generated method stub
        IdentifierGenerator.super.create(creationContext);
    }
    
    
    @Override
    public void initialize(SqlStringGenerationContext context) {
        System.out.println(4);
        // TODO Auto-generated method stub
        IdentifierGenerator.super.initialize(context);
    }
    
}