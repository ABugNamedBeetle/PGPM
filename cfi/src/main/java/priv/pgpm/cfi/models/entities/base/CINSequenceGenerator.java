package priv.pgpm.cfi.models.entities.base;

import java.lang.reflect.Member;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumSet;
import java.util.Properties;

import org.hibernate.annotations.TenantId;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.boot.model.relational.Namespace;
import org.hibernate.engine.jdbc.env.spi.IdentifierHelper;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;
import org.hibernate.generator.GeneratorCreationContext;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.type.descriptor.java.spi.JavaTypeBasicAdaptor;
import org.hibernate.type.descriptor.jdbc.NumericJdbcType;
import org.hibernate.type.internal.NamedBasicTypeImpl;

import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.hibernate.type.descriptor.java.spi.JavaTypeBasicAdaptor;
import org.hibernate.type.descriptor.jdbc.NumericJdbcType;
import org.hibernate.type.internal.NamedBasicTypeImpl;

import static org.hibernate.generator.EventTypeSets.INSERT_ONLY;

public class CINSequenceGenerator extends SequenceStyleGenerator {

    // private final String sqlSelectFrag;
    // private final String entityName;
    
	private final String propertyName;

	public CINSequenceGenerator(CINSequenceID annotation, Member member, GeneratorCreationContext context) {
		// entityName = context.getPersistentClass() == null
		// 		? member.getDeclaringClass().getName() //it's an attribute of an embeddable
		// 		: context.getPersistentClass().getEntityName();
        System.out.println(annotation.name());
		propertyName = context.getProperty().getName();
		// propertyName = ;context.getProperty().getName();
	}
    

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        Object sequenceValue = 1111;
        if (!(sequenceValue instanceof Number)) {
            throw new IllegalArgumentException("Expected a numeric sequence value");
        }

        return "USR" + String.format("%05d", ((Number) sequenceValue).longValue());
    }

    // @Override
    // public void configure(Type type, Properties parameters, ServiceRegistry serviceRegistry) throws MappingException {
    //     parameters.put("increment_size", 1);
    //     Type idType = new NamedBasicTypeImpl<>(new JavaTypeBasicAdaptor<>(Long.class), NumericJdbcType.INSTANCE, "long");
    //     super.configure(idType, parameters, serviceRegistry);
    // }

}

/*

















implements BeforeExecutionGenerator {
    //end::identifiers-IdGeneratorType-example[]
        public static int generationCount = 0;
    
        // private final String sqlSelectFrag;
    private final String entityName;
	private final String propertyName;

	public CINSequenceGenerator(CINSequenceID annotation, Member member, GeneratorCreationContext context) {
		entityName = context.getPersistentClass() == null
				? member.getDeclaringClass().getName() //it's an attribute of an embeddable
				: context.getPersistentClass().getEntityName();
		propertyName = context.getProperty().getName();
	}

        @Override
        public EnumSet<EventType> getEventTypes() {
            return INSERT_ONLY;
        }

        @Override
        public Object generate(SharedSessionContractImplementor session, Object owner, Object currentValue,
                EventType eventType) {
            return "sdsd";
        }
    
    //tag::identifiers-IdGeneratorType-example[]
    
    //     public CINSequenceGenerator(
               
    //             GeneratorCreationContext context) {
    //         //...
    // //end::identifiers-IdGeneratorType-example[]
    //         final String name = "test_seq" ;
    
    //         // ignore the other config for now...
    
    //         final Database database = context.getDatabase();
    //         final IdentifierHelper identifierHelper = database.getJdbcEnvironment().getIdentifierHelper();
    
    //         final Identifier identifier = identifierHelper.toIdentifier( name );
    //         final Namespace defaultNamespace = database.getDefaultNamespace();
    //         org.hibernate.boot.model.relational.Sequence sequence = defaultNamespace.locateSequence( identifier );
    //         if ( sequence == null ) {
    //             sequence = defaultNamespace.createSequence(
    //                     identifier,
    //                     (physicalName) -> new org.hibernate.boot.model.relational.Sequence(
    //                             null,
    //                             defaultNamespace.getPhysicalName().getCatalog(),
    //                             defaultNamespace.getPhysicalName().getSchema(),
    //                             physicalName,
    //                             1,
    //                             50
    //                     )
    //             );
    //         }
    
    //         this.sqlSelectFrag =
    //                 database.getDialect().getSequenceSupport()
    //                         .getSequenceNextValString( sequence.getName().getSequenceName().render( database.getDialect() ) );
    
    // //tag::identifiers-IdGeneratorType-example[]
    //     }
    
    //     @Override
    //     public Object generate(
    //             SharedSessionContractImplementor session,
    //             Object object) {
    //         //...
    // //end::identifiers-IdGeneratorType-example[]
    //         generationCount++;
    //         try {
    //             final PreparedStatement st = session.getJdbcCoordinator().getStatementPreparer().prepareStatement( sqlSelectFrag );
    //             try {
    //                 final ResultSet rs = session.getJdbcCoordinator().getResultSetReturn().extract( st, sqlSelectFrag );
    //                 try {
    //                     rs.next();
    //                     return rs.getInt( 1 );
    //                 }
    //                 finally {
    //                     try {
    //                         session.getJdbcCoordinator().getLogicalConnection().getResourceRegistry().release( rs, st );
    //                     }
    //                     catch( Throwable ignore ) {
    //                         // intentionally empty
    //                     }
    //                 }
    //             }
    //             finally {
    //                 session.getJdbcCoordinator().getLogicalConnection().getResourceRegistry().release( st );
    //                 session.getJdbcCoordinator().afterStatementExecution();
    //             }
    
    //         }
    //         catch ( SQLException sqle) {
    //             throw session.getJdbcServices().getSqlExceptionHelper().convert(
    //                     sqle,
    //                     "could not get next sequence value",
    //                     sqlSelectFrag
    //             );
    //         }
    //     }
    //tag::identifiers-IdGeneratorType-example[]
    }
    //end::identifiers-IdGeneratorType-example[]  */