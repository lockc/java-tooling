package lockc.spring.examples.core.jmx;

import org.springframework.jmx.export.annotation.*;

@ManagedResource
public class AnotherJmxTestBean {

    private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


}
