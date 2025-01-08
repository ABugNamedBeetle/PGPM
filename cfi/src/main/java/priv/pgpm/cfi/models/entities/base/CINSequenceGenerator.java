package priv.pgpm.cfi.models.entities.base;

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;
import java.util.Properties;

import org.hibernate.annotations.TenantId;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.boot.model.relational.Namespace;
import org.hibernate.engine.jdbc.env.spi.IdentifierHelper;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.AnnotationBasedGenerator;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;
import org.hibernate.generator.GeneratorCreationContext;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.type.descriptor.java.spi.JavaTypeBasicAdaptor;
import org.hibernate.type.descriptor.jdbc.NumericJdbcType;
import org.hibernate.type.internal.NamedBasicTypeImpl;
import org.apache.tomcat.util.net.jsse.PEMFile;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.hibernate.type.descriptor.java.spi.JavaTypeBasicAdaptor;
import org.hibernate.type.descriptor.jdbc.NumericJdbcType;
import org.hibernate.type.internal.NamedBasicTypeImpl;

import static org.hibernate.generator.EventTypeSets.INSERT_ONLY;

public class CINSequenceGenerator implements IdentifierGenerator, AnnotationBasedGenerator<CINSequenceID>{

    private String GET_CURRENT_VALUE_QUERY;
    private String UPDATE_NEXT_VALUE_QUERY;
    private Long limit = null;
    private Long curr = null;

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        if(curr == null || curr == limit-1){
            curr = session.doReturningWork(connection -> {
                Long currentValue = null;
                 // Get the current value
                 try (PreparedStatement getStmt = connection.prepareStatement(GET_CURRENT_VALUE_QUERY)) {
                     
                     try (ResultSet rs = getStmt.executeQuery()) {
                         if (rs.next()) {
                             currentValue = rs.getLong(1);
                         } else {
                             throw new SQLException("Sequence not found");
                         }
                     }
                 }
     
                 // Calculate the next value
                 Long nextValue = currentValue + 50;
     
                 // Update the next value in the database
                 try (PreparedStatement updateStmt = connection.prepareStatement(UPDATE_NEXT_VALUE_QUERY)) {
                     updateStmt.setLong(1, nextValue);
                     
                     int rowsUpdated = updateStmt.executeUpdate();
                     if (rowsUpdated != 1) {
                         throw new SQLException("Failed to update the sequence");
                     }
                 }
     
                 // Return the current value for the generated identifier
                 System.out.println("generated = "+ currentValue);
                 System.out.println("next val = "+ nextValue);
                 return currentValue;
             });
            limit = curr + 50;
        }else{

            curr++;
        }
        
        
        return String.format("CIN%s%d", LocalDate.now().format( DateTimeFormatter.ofPattern("YYMMDD")), curr);
    }

    

   

    @Override
    public void initialize(CINSequenceID config, Member member, GeneratorCreationContext context) {
       String entityName = config.name();
       System.out.println(entityName);
       this.GET_CURRENT_VALUE_QUERY = "SELECT value FROM "+entityName+" WHERE id = 1";
        this.UPDATE_NEXT_VALUE_QUERY = "UPDATE "+entityName+" SET value = ? WHERE id = 1";
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