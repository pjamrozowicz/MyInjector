package myinjector.Scopes;


import myinjector.AbstractSettings;
import myinjector.Builder;

public class Singleton implements IScope{

    private boolean alreadyCreated = false;
    private Object createdObject;

    public <T> T getInstance(Class clazz, AbstractSettings settings) {
        if(alreadyCreated){
            return (T) createdObject;
        } else {
            alreadyCreated = true;
            createdObject = new Builder(settings).createInstance(clazz);
            return (T) createdObject;
        }
    }
}
