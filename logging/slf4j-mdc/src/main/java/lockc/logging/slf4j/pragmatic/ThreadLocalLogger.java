package lockc.logging.slf4j.pragmatic;

import org.slf4j.Logger;

/**
 * 
 * 
 *
 */
public class ThreadLocalLogger
{
    private static ThreadLocal<Logger> threadLocalLogger = new ThreadLocal<Logger>()
    {
        @Override
        protected Logger initialValue()
        {
            return null;
        }
    };

    public static void setLogger(Logger logger)
    {
        threadLocalLogger.set(logger);
    }

    public static Logger getLogger()
    {
        return threadLocalLogger.get();
    }

    public static void destroy()
    {
        threadLocalLogger.remove();
        threadLocalLogger = null;
    }

}
