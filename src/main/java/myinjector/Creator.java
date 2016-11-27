package myinjector;


public class Creator {
    public static <T> T CreateInstanceOf(Class clazz){
        return (T)new Object();
    }
}
