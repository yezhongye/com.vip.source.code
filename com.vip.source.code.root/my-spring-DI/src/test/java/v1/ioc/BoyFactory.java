package v1.ioc;

public class BoyFactory {
    
    public static Boy getBean() {
        return new Lad();
    }
}
