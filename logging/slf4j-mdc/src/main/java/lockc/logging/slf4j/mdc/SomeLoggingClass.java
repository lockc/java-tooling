package lockc.logging.slf4j.mdc;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class SomeLoggingClass implements Runnable
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SomeLoggingClass.class);

    public SomeLoggingClass()
    {
        String uuidValue = UUID.randomUUID().toString();
        MDC.put("id", uuidValue);
    }

    public void doSomething()
    {
        LOGGER.info("I am doing something damn it!");
    }

    @Override
    public void run()
    {
        while (true)
        {
            LOGGER.info("I am going to keep going until you tell me to stop..");
            try
            {

                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    }
}
