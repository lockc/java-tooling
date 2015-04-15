package lockc.dozer.examples;

import java.util.Set;

public class TypeD extends TypeC {
    
    private Set<String> fieldA;
    private String fieldB;
    private Number fieldY;
    
    public Set<String> getFieldA() {
    
        return fieldA;
    }
    
    public void setFieldA(Set<String> fieldA) {
    
        this.fieldA = fieldA;
    }
    
    public String getFieldB() {
    
        return fieldB;
    }
    
    public void setFieldB(String fieldB) {
    
        this.fieldB = fieldB;
    }
    
    public Number getFieldY() {
    
        return fieldY;
    }
    
    public void setFieldY(Number fieldY) {
    
        this.fieldY = fieldY;
    }
    
    @Override
    public String toString() {
    
        StringBuilder sb = new StringBuilder();
        
        // @formatter:off
        sb.append("{fieldA:").append(fieldA)
            .append("}, {fieldB:").append(fieldB)
            .append("}, {fieldY:").append(fieldY)
            .append("}");
        // @formatter:on
        
        return sb.toString();
    }
    
}
