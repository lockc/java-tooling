package lockc.dozer.examples.converters;

import java.util.Calendar;
import java.util.Date;

import org.dozer.DozerConverter;


public class DateIntConverter extends DozerConverter<Date, Long> {
    
    public DateIntConverter() {
        super(Date.class, Long.class);
    }

    @Override
    public Long convertTo(Date source, Long destination) {
    
        return source.getTime();
    }

    @Override
    public Date convertFrom(Long source, Date destination) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(source);
        return c.getTime();
    }
}
