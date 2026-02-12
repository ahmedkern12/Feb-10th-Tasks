import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface LogExecutionTime {
}

class Logger {
    private static Logger obj = new Logger();
    private Logger() {}
    static Logger getInstance() {
        return obj;
    }
    void log(String msg) {
        System.out.println(msg);
    }
}

class OrderService {
    @LogExecutionTime
    public void placeOrder() {
        for(int i=0;i<1000000;i++);
        System.out.println("Order placed");
    }

    public void cancelOrder() {
        System.out.println("Order cancelled");
    }
}

class UserService {
    @LogExecutionTime
    public void addUser() {
        for(int i=0;i<500000;i++);
        System.out.println("User added");
    }

    public void deleteUser() {
        System.out.println("User deleted");
    }
}

class FrameworkRunner {
    static void run(Object obj) throws Exception {
        Method[] m = obj.getClass().getDeclaredMethods();
        for(Method method : m) {
            if(method.isAnnotationPresent(LogExecutionTime.class)) {
                long start = System.currentTimeMillis();
                method.invoke(obj);
                long end = System.currentTimeMillis();
                Logger.getInstance().log(method.getName()+" time: "+(end-start)+" ms");
            } else {
                method.invoke(obj);
            }
        }
    }
}

public class Task10 {
    public static void main(String[] args) throws Exception {
        FrameworkRunner.run(new OrderService());
        FrameworkRunner.run(new UserService());
    }
}
