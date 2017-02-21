package myinjector;


public interface IMyInjector {
    <T> T get(Class clazz);

    void turnOnLogger();
    void turnOffLogger();
}
