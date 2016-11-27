package myinjector;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractInjector {

    private Map<Class, Class> bindings;

    public AbstractInjector() {
        this.bindings = new HashMap<Class, Class>();
        load();
    }

    public abstract void load();

    public void addBinding(Class sourceClass, Class outputClass){
            this.bindings.put(sourceClass, outputClass);
    }

    public void addNamedBinding(Class sourceClass, Class dependencyClass, String name){

    }

    public <T> T getInstanceOf(Class clazz){
        Class classToCreate;

        if(bindings.containsKey(clazz)) {
            classToCreate = bindings.get(clazz);
        } else {
            //TODO: decide what to do when there is no binding
            classToCreate = clazz;
        }

        return Creator.CreateInstanceOf(classToCreate);
    }

}
