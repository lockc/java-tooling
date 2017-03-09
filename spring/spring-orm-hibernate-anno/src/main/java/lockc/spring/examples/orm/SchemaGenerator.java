package lockc.spring.examples.orm;

import lockc.spring.examples.orm.domain.BankDetails;
import lockc.spring.examples.orm.domain.Customer;
import lockc.spring.examples.orm.domain.Order;
import lockc.spring.examples.orm.domain.PersonalDetails;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * @author Chris
 */
public class SchemaGenerator {

    private AnnotationConfiguration cfg;

    public SchemaGenerator() throws Exception {
        cfg = new AnnotationConfiguration();
        cfg.setProperty("hibernate.hbm2ddl.auto","create");
        cfg.addAnnotatedClass(Customer.class);
        cfg.addAnnotatedClass(PersonalDetails.class);
        cfg.addAnnotatedClass(BankDetails.class);
        cfg.addAnnotatedClass(Order.class);
    }

    private void generate() {
        cfg.setProperty("hibernate.dialect", SQLiteDialect.class.getName());
        SchemaExport export = new SchemaExport(cfg);
        export.setDelimiter(";");
        export.setOutputFile("ddl_create.sql");
        export.execute(true, false, false, false);
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        SchemaGenerator gen = new SchemaGenerator();
        gen.generate();
    }

}
