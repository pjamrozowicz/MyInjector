package myinjector.Scopes;


import myinjector.BindingInfo;
import myinjector.ComponentBuilder;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Singleton implements IScope{

    private boolean alreadyCreated = false;
    private Object createdObject;
    private BindingInfo bindingInfo;
    private static final Logger logger = Logger.getLogger("myinjector");


    public Singleton(BindingInfo bindingInfo){
        this.bindingInfo = bindingInfo;
    }

    public <T> T getInstance(ComponentBuilder componentBuilder) {
        if(alreadyCreated){
            logger.log(Level.INFO, "Singleton object " + createdObject.getClass().getSimpleName() + " already created.");
            return (T) createdObject;
        } else {
            alreadyCreated = true;
            createdObject = componentBuilder.createInstance(bindingInfo);
            return (T) createdObject;
        }
    }
}
