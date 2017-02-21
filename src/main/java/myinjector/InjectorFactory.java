package myinjector;


public class InjectorFactory {
    public static IMyInjector getInjector(String name, AbstractSettings settings){

        if(name.equalsIgnoreCase("DefaultInjector")){
            return new DefaultInjector(settings);
        }

        if(name.equalsIgnoreCase("SecondInjector")){
            return new DefaultInjector(settings);
        }

        return new DefaultInjector(settings);
    }
}
