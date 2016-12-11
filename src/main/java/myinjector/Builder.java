package myinjector;

import myinjector.Annotations.Inject;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Comparator;

public class Builder{

    private Injector injector;

    public Builder(AbstractSettings settings){
        this.injector = new Injector(settings);
    }

    public <T> T createInstance(Class clazz) {
        System.out.println("Creating object: " + clazz.getName());
        Constructor constructor = findConstructor(clazz);
        Object[] arguments = injector.injectArguments(constructor);
        T outputObject = null;
        try {
            outputObject = (T)constructor.newInstance(arguments);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        injector.injectDependencies(outputObject);
        return outputObject;
    }


    private Constructor findConstructor(Class clazz) {
        Constructor[] allConstructors = clazz.getConstructors();
        if(allConstructors.length == 0){
            Constructor constructor = null;
            try {
                constructor = clazz.getConstructor();
            } catch (NoSuchMethodException e) {
                //@TODO: display info that clazz can not be created
            }
            return constructor;
        }
        for (Constructor constructor: allConstructors) {
            if(isExplicitInjectionConstructor(constructor)){
                return constructor;
            }
        }

        return findImplicitConstructor(allConstructors);
    }

    private boolean isExplicitInjectionConstructor(Constructor constructor)
    {
        return (constructor.getAnnotation(Inject.class) != null);
    }

    private Constructor findImplicitConstructor(Constructor[] constructors) {
        Arrays.sort(constructors, new Comparator<Constructor>() {
            public int compare(Constructor constr1, Constructor constr2){
                if(constr1.getParameterTypes().length >= constr2.getParameterTypes().length)
                    return -1;
                else return 1;
            }
        });

        return constructors[0];
    }
}
