package myinjector.Injectors;

import myinjector.Annotations.Inject;
import myinjector.Creator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
/*
public class ConstructorInjector extends Creator{

    private <T> T getInstance(Class clazz) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructor = findConstructor(clazz);
        Object[] arguments = resolveConstructorArguments(constructor);
        T outputObject = (T)constructor.newInstance(arguments);
        resolveDependiences(outputObject);
        return outputObject;
    }

    private Constructor findConstructor(Class clazz) {
        System.out.println("Looking for constructor for: " + clazz.getName());
        Constructor[] allConstructors = clazz.getConstructors();
        for (Constructor constructor: allConstructors) {
            if(isExplicitInjectionConstructor(constructor)){
                System.out.println("Found explicit constructor");
                return constructor;
            }
        }

        return findImplicitConstructor(clazz);
    }

    private Constructor findImplicitConstructor(Class clazz) {
        Constructor[] constructors = clazz.getConstructors();
        Arrays.sort(constructors, new Comparator<Constructor>() {
            public int compare(Constructor constr1, Constructor constr2){
                if(constr1.getParameterTypes().length >= constr2.getParameterTypes().length)
                    return -1;
                else return 1;
            }
        });

        return null; //@TODO: change to enumerable, so that it always gives next constructor in order
    }

    private Object[] resolveConstructorArguments(Constructor constructor) {
        System.out.println("Resolving arguments for constructor of: " + constructor.getName());
        if(constructor.getParameterTypes().length > 0){
            System.out.println("Found arguments, resolving them.");
            List<Object> arguments = new ArrayList<Object>();
            for (Class argument:constructor.getParameterTypes()) {
                arguments.add(resolve(argument));
            }
            return arguments.toArray();
        }
        System.out.println("Didn't find arguments.");
        return null; //@TODO: add checking for annotations and use resolveNamed in case of finding one
    }

    private boolean isExplicitInjectionConstructor(Constructor constructor)
    {
        return (constructor.getAnnotation(Inject.class) != null);
    }

    private <T> void resolveDependiences(T object){
        checkForMethodDependencies(object);
        checkForFieldDependencies(object);
    }

    private <T> void checkForMethodDependencies(T object) {
        Method[] methods = object.getClass().getMethods();
        for(Method method : methods){
            if(method.getAnnotation(Inject.class) != null){
                for()
            }
        }
    }

    private <T> void checkForFieldDependencies(T object) {
        Field[] fields = object.getClass().getFields();
        for(Field field : fields){
            if(field.getAnnotation(Inject.class) != null){
                // wywolaj resolve'a na klasie pola i wstrzyknij je
            }
        }
    }
}
*/