package lockc.logging.slf4j.pragmatic;

import static lockc.logging.slf4j.pragmatic.ThreadLocalLogger.getLogger;

import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class SomeLoggingClass implements Runnable
{

    private String loggerName;

    public SomeLoggingClass(String loggerName)
    {
        String uuidValue = UUID.randomUUID().toString();
        MDC.put("id", uuidValue);
        this.loggerName = loggerName;

    }

    @Override
    public void run()
    {
        AnotherLoggingClass anotherClass = new AnotherLoggingClass();

        ThreadLocalLogger.setLogger(LoggerFactory.getLogger(loggerName));
        while (true)
        {
            getLogger().info("I am going to keep going until you tell me to stop..");
            anotherClass.doSomething();
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
