package lockc.logging.slf4j.pragmatic;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class Main
{
    private static final Executor TASKS = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws Exception
    {

        ConsoleAppender console = new ConsoleAppender(); // create appender
        // configure the appender
        console.setName("console");
        String PATTERN = "%d{yyyy-MMM-dd HH:mm:ss.SSS} | %X{id} | %c{1} | %-5p | %C{1} | %m%n";
        console.setLayout(new PatternLayout(PATTERN));
        console.setThreshold(Level.DEBUG);
        console.activateOptions();
        // add appender to any Logger (here is root)
        Logger.getRootLogger().addAppender(console);

        FileAppender fa = new FileAppender();
        fa.setName("FileLogger");
        fa.setFile("mylog.log");
        fa.setLayout(new PatternLayout("CONSOLE - " + PATTERN));
        fa.setThreshold(Level.DEBUG);
        fa.setAppend(true);
        fa.activateOptions();

        // add appender to any Logger (here is root)
        Logger.getLogger("FileLogger").addAppender(fa);

        FileAppender fa2 = new FileAppender();
        fa2.setName("FileLogger2");
        fa2.setFile("mylog2.log");
        fa2.setLayout(new PatternLayout("FILE - " + PATTERN));
        fa2.setThreshold(Level.DEBUG);
        fa2.setAppend(true);
        fa2.activateOptions();

        // add appender to any Logger (here is root)
        Logger.getLogger("FileLogger2").addAppender(fa2);

        TASKS.execute(new SomeLoggingClass("FileLogger"));
        Thread.sleep(200);

        TASKS.execute(new SomeLoggingClass("FileLogger2"));
        Thread.sleep(200);

        TASKS.execute(new SomeLoggingClass("FileLogger"));
        Thread.sleep(200);

        TASKS.execute(new SomeLoggingClass("FileLogger2"));
        Thread.sleep(200);

    }
}
