package lockc.camel.examples;

public class TypeA {
    
    private String fieldA;
    private Integer fieldB;
    private long fieldC;
    private Object fieldX;

    
    public String getFieldA() {
    
        return fieldA;
    }

    
    public void setFieldA(String fieldA) {
    
        this.fieldA = fieldA;
    }

    
    public Integer getFieldB() {
    
        return fieldB;
    }

    
    public void setFieldB(Integer fieldB) {
    
        this.fieldB = fieldB;
    }

    
    public long getFieldC() {
    
        return fieldC;
    }

    
    public void setFieldC(long fieldC) {
    
        this.fieldC = fieldC;
    }

    
    public Object getFieldX() {
    
        return fieldX;
    }

    
    public void setFieldX(Object fieldX) {
    
        this.fieldX = fieldX;
    }
    
    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("{fieldA:").append(fieldA)
            .append("}, {fieldB:").append(fieldB)
            .append("}, {fieldC:").append(fieldC)
            .append("}, {fieldX:").append(fieldX)
            .append("}");
        
        return sb.toString();
    }
    
}
