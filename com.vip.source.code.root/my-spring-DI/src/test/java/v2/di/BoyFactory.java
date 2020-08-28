package v2.di;

public class BoyFactory {
    
    public static Boy getBean(String name, Money m) {
        return new Lad(name, m);
    }
}
