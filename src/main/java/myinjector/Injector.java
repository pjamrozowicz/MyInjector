package myinjector;


import myinjector.Annotations.Inject;
import myinjector.Annotations.Named;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class Injector {

    private Resolver resolver;

    public Injector(AbstractSettings settings){
        this.resolver = Resolver.getInstance(settings);
    }

    public <T> void injectDependencies(T object){
        System.out.println("Injecting dependencies for: " + object.getClass().getName());
        injectFieldDependencies(object);
        injectMethodDependencies(object);
    }

    private <T> void injectMethodDependencies(T object) {
        Method[] methods = object.getClass().getDeclaredMethods();
        for(Method method : methods){
            if(method.getAnnotation(Inject.class) != null){
                System.out.println("Found method injection: " + method.getName());
                method.setAccessible(true);
                System.out.println("Checking method: " + method.getName());
                Object[] arguments = injectArguments(method);
                try {
                    method.invoke(object,arguments);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private <T> void injectFieldDependencies(T object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for(Field field : fields){
            if(field.getAnnotation(Inject.class) != null){
                field.setAccessible(true);
                Object createdObject = resolver.resolve(field.getType());
                try {
                    field.set(object, createdObject);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Object[] injectArguments(Executable executable) {
        if(executable.getParameterTypes().length > 0){
            List<Object> arguments = new ArrayList<Object>();
            for(Parameter parameter:executable.getParameters()){
                Named named = parameter.getAnnotation(Named.class);
                if(named != null){
                    arguments.add(resolver.resolveNamed(parameter.getType(),named.name()));
                } else {
                    arguments.add(resolver.resolve(parameter.getType()));
                }
            }
            return arguments.toArray();
        }
        return null;
    }
}
