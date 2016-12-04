package myinjector;


import myinjector.Annotations.Inject;
import myinjector.Annotations.Singleton;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Creator {

    private AbstractSettings settings;

    public Creator(AbstractSettings settings){
        this.settings = settings;
    }

    public <T> T resolve(Class clazz){
        System.out.println("Resolving class: " + clazz.getName());
        BindingInfo bindingInfo = settings.getBindingInfo(clazz);
        return resolve(clazz, bindingInfo);
    }

    public <T> T resolveNamed(Class clazz, String name){
        BindingInfo bindingInfo = settings.getNamedBindingInfo(clazz, name);
        return resolve(clazz, bindingInfo);
    }

    private <T> T resolve(Class clazz, BindingInfo bindingInfo){
        T output = null;
        if(bindingInfo == null){
            if(clazz.isInterface()){
                //@TODO: throw error, can't create new object from interface
                return null;
            } else if(Modifier.isAbstract(clazz.getModifiers())){
                //@TODO: throw error, can't create new object from abstract class
                return null;
            }
            else {
                try {
                    output =  getInstance(clazz);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
                return output;
            }
        } else {
            try {
                output = getInstance(bindingInfo);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return output;
        }
    }


    private <T> T getInstance(Class clazz) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        if(classIsSingleton(clazz)){
            return createSingletonInstance(clazz);
        }

        System.out.println("creating new instance of " + clazz.getName());
        return createInstance(clazz);
    }

    private <T> T getInstance(BindingInfo bindingInfo) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        if(bindingInfo.isSingleton()){
            return createSingletonInstance(bindingInfo.getClazz());
        }
        System.out.println("creating new instance of " + bindingInfo.getClazz().getName());
        return createInstance(bindingInfo.getClazz());
    }

    private <T> T createInstance(Class clazz) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructor = findConstructor(clazz);
        Object[] arguments = resolveConstructorArguments(constructor);
        T outputObject = (T)constructor.newInstance(arguments);
        resolveDependiences(outputObject);
        return outputObject;
    }

    private <T> T createSingletonInstance(Class clazz) {
        return null;
    }

    private boolean classIsSingleton(Class clazz){
        return clazz.getAnnotation(Singleton.class) != null;
    }

    private Constructor findConstructor(Class clazz) {
        Constructor[] allConstructors = clazz.getConstructors();
        for (Constructor constructor: allConstructors) {
            if(isExplicitInjectionConstructor(constructor)){
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
        if(constructor.getParameterTypes().length > 0){
            List<Object> arguments = new ArrayList<Object>();
            for (Class argument:constructor.getParameterTypes()) {
                arguments.add(resolve(argument));
            }
            return arguments.toArray();
        }
        return null; //@TODO: add checking for annotations and use resolveNamed in case of finding one
    }

    private boolean isExplicitInjectionConstructor(Constructor constructor)
    {
        return (constructor.getAnnotation(Inject.class) != null);
    }

    private <T> void resolveDependiences(T object){
        System.out.println("Resolving dependencies for: " + object.getClass().getName());
        checkForMethodDependencies(object);
        checkForFieldDependencies(object);
    }

    private <T> void checkForMethodDependencies(T object) {
        Method[] methods = object.getClass().getDeclaredMethods();
        for(Method method : methods){
            if(method.getAnnotation(Inject.class) != null){
                System.out.println("Found method injection: " + method.getName());
                method.setAccessible(true);
                System.out.println("Checking method: " + method.getName());
                Object[] arguments = resolveMethodArguments(method);
                try {
                    method.invoke(object,arguments);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private Object[] resolveMethodArguments(Method method) {
        if(method.getParameterTypes().length > 0){
            List<Object> arguments = new ArrayList<Object>();
            for(Class argument:method.getParameterTypes()){
                arguments.add(resolve(argument));
            }
            return arguments.toArray();
        }

        return null;
    }

    private <T> void checkForFieldDependencies(T object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for(Field field : fields){
            if(field.getAnnotation(Inject.class) != null){
                field.setAccessible(true);
                Object createdObject = resolve(field.getType());
                try {
                    field.set(object, createdObject);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
