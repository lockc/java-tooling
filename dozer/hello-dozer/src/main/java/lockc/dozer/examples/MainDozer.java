package lockc.dozer.examples;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * 
 * 
 * 
 */
public class MainDozer {
    
    private static final Logger LOG = Logger.getLogger(MainDozer.class);
    
    
    public static void main(String[] args) throws Exception {
    
        Mapper mapper = new DozerBeanMapper();
        
        TypeA typeA = new TypeA();
        typeA.setFieldA("abcd");
        typeA.setFieldB(10);
        LOG.info(typeA);
        
        TypeB mappedB = mapper.map(typeA, TypeB.class);
        LOG.info(mappedB);
        
        TypeA mappedA = mapper.map(mappedB, TypeA.class);
        LOG.info(mappedA);
                
        
    }
    
}
