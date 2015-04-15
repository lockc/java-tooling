package lockc.spring.examples.core.jmx;

import org.springframework.jmx.export.annotation.*;

@ManagedResource
public class JmxAnnotatedTestBean {

    private String name;
    private int age;

    @ManagedAttribute
    public int getAge() {
        return age;
    }

    @ManagedAttribute
    public void setAge(int age) {
        this.age = age;
    }
    
    @ManagedAttribute
    public void setName(String name) {
        this.name = name;
    }

    @ManagedAttribute
    public String getName() {
        return name;
    }

    @ManagedAttribute
    public int add(int x, int y) {
        return x + y;
    }

    public void dontExposeMe() {
        throw new RuntimeException();
    }
    
    private void wontBeExposed() {
    	throw new RuntimeException();
    }
    
    protected void wontBeExposedEither() {
    	throw new RuntimeException();
    }
}
