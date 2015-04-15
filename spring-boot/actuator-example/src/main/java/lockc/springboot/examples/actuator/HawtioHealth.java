package lockc.springboot.examples.actuator;

import java.util.Arrays;
import java.util.List;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

/**
 * This managed bean is picked up by hawt.io simply from
 * the bean name service=Health. Providing this gives you the
 * 'Health' tab on the management console.
 *
 * @author lockc
 *
 */
@Component
@ManagedResource(objectName = "bean:service=Health")
public class HawtioHealth {
    
    public static final String HEALTH_ID = HawtioHealth.class.getSimpleName();
    
    @ManagedOperation
    public Object health() {
    
        return new HawtioHealthStatus("A1", "INFO", "WOW!", "Some resource");
    }

    @ManagedOperation
    public List<Object> healthList() {
    
        return Arrays.asList(new HawtioHealthStatus("AA", "INFO", "WOW...", "Some resource"), new HawtioHealthStatus(
                "BB", "WARN", "HMM!", "Some other resource"), new HawtioHealthStatus("CC", "ERROR", "OOPS!!",
                "Some other resource"));
    }
    
    public static class HawtioHealthStatus {

        private String healthId = HawtioHealth.HEALTH_ID;

        private String level;

        private String message;

        private String resource;
        
        public HawtioHealthStatus(String id, String level, String message, String resource) {
        
            super();
            this.healthId = id + "." + id;
            this.level = level;
            this.message = message;
            this.resource = resource;
        }
        
        public String getHealthId() {
        
            return healthId;
        }
        
        public String getLevel() {
        
            return level;
        }
        
        public String getMessage() {
        
            return message;
        }
        
        public String getResource() {
        
            return resource;
        }
    }

}
