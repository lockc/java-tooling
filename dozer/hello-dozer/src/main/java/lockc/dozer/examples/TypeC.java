package lockc.dozer.examples;

import java.util.List;


public class TypeC {
 
    protected boolean fieldA;
    protected String fieldB;
    private List<Object> fieldC;
    private List<TypeA> fieldD;
    
    public boolean isFieldA() {
    
        return fieldA;
    }
    
    public void setFieldA(boolean fieldA) {
    
        this.fieldA = fieldA;
    }
    
    public String getFieldB() {
    
        return fieldB;
    }
    
    public void setFieldB(String fieldB) {
    
        this.fieldB = fieldB;
    }
    
    public List<Object> getFieldC() {
    
        return fieldC;
    }
    
    public void setFieldC(List<Object> fieldC) {
    
        this.fieldC = fieldC;
    }
    
    public List<TypeA> getFieldD() {
    
        return fieldD;
    }
    
    public void setFieldD(List<TypeA> fieldD) {
    
        this.fieldD = fieldD;
    }
    
    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("{fieldA:").append(fieldA)
            .append("}, {fieldB:").append(fieldB)
            .append("}, {fieldC:").append(fieldC)
            .append("}, {fieldD:").append(fieldD)
            .append("}");
        
        return sb.toString();
    }
    
}
