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
public class MainDozerXml {
    
    private static final Logger LOG = Logger.getLogger(MainDozerXml.class);
    
    
    public static void main(String[] args) throws Exception {
    
        List<String> mappingFiles = new ArrayList<>();
        mappingFiles.add("basic-mapping.xml");
        
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(mappingFiles);
        
        TypeA typeA = new TypeA();
        typeA.setFieldA("abcd");
        typeA.setFieldB(10);
        typeA.setFieldX(Calendar.getInstance().getTime());
        LOG.info(typeA);
        
        TypeB mappedB = mapper.map(typeA, TypeB.class);
        LOG.info(mappedB);
        
        TypeA mappedA = mapper.map(mappedB, TypeA.class);
        LOG.info(mappedA);

        
    }
    
}
