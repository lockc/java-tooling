package lockc.springboot.examples.actuator;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.stereotype.Component;

@Component
public class MyPublicMetric implements PublicMetrics {
    
    public static final String NAME = "my.public.metric";
    
    private Collection<Metric<?>> metrics = new ArrayList<Metric<?>>();

    @Override
    public Collection<Metric<?>> metrics() {
    
        return metrics;
    }
    
    public void record(double value) {
    
        metrics.add(new Metric<Number>(MyPublicMetric.NAME, value));
    }
    
}
