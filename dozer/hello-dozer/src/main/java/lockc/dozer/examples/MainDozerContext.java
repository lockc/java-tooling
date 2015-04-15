package lockc.dozer.examples;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;

/**
 * 
 * 
 * 
 */
public class MainDozerContext {
    
    private static final Logger LOG = Logger.getLogger(MainDozerContext.class);
    
    
    public static void main(String[] args) throws Exception {
    
        List<String> mappingFiles = new ArrayList<>();
        mappingFiles.add("context-mapping.xml");
        
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(mappingFiles);
        
        
        TypeA typeA = new TypeA();
        typeA.setFieldA("abcd");
        typeA.setFieldB(10);
        typeA.setFieldX(Calendar.getInstance().getTime());
        LOG.info(typeA);
        
        TypeB mappedB = mapper.map(typeA, TypeB.class, "case-a");
        LOG.info(mappedB);
        
        TypeB mappedB2 = mapper.map(typeA, TypeB.class, "case-b");
        LOG.info(mappedB2);
        
    }
    
}
    