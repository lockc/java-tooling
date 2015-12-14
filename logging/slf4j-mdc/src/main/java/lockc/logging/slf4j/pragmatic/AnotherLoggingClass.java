package lockc.logging.slf4j.pragmatic;

import static lockc.logging.slf4j.pragmatic.ThreadLocalLogger.getLogger;

public class AnotherLoggingClass
{

    public void doSomething()
    {
        getLogger().debug("I am doing something damn it!");
    }
}
