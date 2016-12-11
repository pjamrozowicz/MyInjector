package myinjector.Scopes;


import myinjector.AbstractSettings;
import myinjector.Builder;

public class Prototype implements IScope{

    public <T> T getInstance(Class clazz, AbstractSettings settings) {
        System.out.println("Prototype called for class " + clazz.getName());
        return new Builder(settings).createInstance(clazz);
    }
}
