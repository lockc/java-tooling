package lockc.logging.slf4j.mdc;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main
{
    private static final Executor TASKS = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws Exception
    {
        SomeLoggingClass somClass = new SomeLoggingClass();
        somClass.doSomething();

        TASKS.execute(new SomeLoggingClass());
        Thread.sleep(200);

        TASKS.execute(new SomeLoggingClass());
        Thread.sleep(200);

        TASKS.execute(new SomeLoggingClass());
        Thread.sleep(200);

        TASKS.execute(new SomeLoggingClass());
        Thread.sleep(200);
    }
}
