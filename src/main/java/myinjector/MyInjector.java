package myinjector;

public class MyInjector {

    private AbstractSettings settings;
    private Resolver resolver;

    public MyInjector(AbstractSettings settings){
        this.settings = settings;
        this.resolver = Resolver.getInstance(settings);
    }

    public <T> T get(Class clazz){
        System.out.println("Someone wants to create " + clazz.getName());
        return resolver.resolve(clazz);
    }

}
