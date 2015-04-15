package lockc.camel.examples;

import java.util.Date;

public class TypeB {
    
    private String fieldA;
    private long fieldB;
    private Date fieldC;
    
    public String getFieldA() {
    
        return fieldA;
    }
    
    public void setFieldA(String fieldA) {
    
        this.fieldA = fieldA;
    }
    
    public long getFieldB() {
    
        return fieldB;
    }
    
    public void setFieldB(long fieldB) {
    
        this.fieldB = fieldB;
    }
    
    public Date getFieldC() {
    
        return fieldC;
    }
    
    public void setFieldC(Date fieldC) {
    
        this.fieldC = fieldC;
    }
    
    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("{fieldA:").append(fieldA)
            .append("}, {fieldB:").append(fieldB)
            .append("}, {fieldC:").append(fieldC)
            .append("}");
        
        return sb.toString();
    }
}
