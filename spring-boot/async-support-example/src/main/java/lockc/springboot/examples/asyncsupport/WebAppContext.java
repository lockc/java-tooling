package lockc.springboot.examples.asyncsupport;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
public class WebAppContext extends WebMvcConfigurerAdapter {
    
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
    
        configurer.setTaskExecutor(new AsyncTaskExecutor() {

            private ExecutorService exec = Executors.newCachedThreadPool(new ThreadFactory() {
                
                private AtomicLong threadNumber = new AtomicLong(1);

                @Override
                public Thread newThread(Runnable r) {
                
                    return new Thread(r, "ControllerThread-" + threadNumber.getAndIncrement());
                }
            });

            @Override
            public void execute(Runnable task) {
            
                exec.execute(task);
            }

            @Override
            public <T> Future<T> submit(Callable<T> task) {
            
                return exec.submit(task);
            }

            @Override
            public Future<?> submit(Runnable task) {
            
                return exec.submit(task);
            }

            @Override
            public void execute(Runnable task, long startTimeout) {
            
                throw new RuntimeException("not supported.");
            }
        });
    }
}
