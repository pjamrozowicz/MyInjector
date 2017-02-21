package myinjector;

import myinjector.Dependencies.ClassDependencies;
import myinjector.Dependencies.ConstructorDependency;
import myinjector.Dependencies.FieldDependency;
import myinjector.Dependencies.MethodDependency;

import java.lang.reflect.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComponentBuilder {
    private static final Logger logger = Logger.getLogger("myinjector");

    public <T> T createInstance(BindingInfo bindingInfo) {
        if(bindingInfo.getInstance() != null){
            return (T) bindingInfo.getInstance();
        }

        if(bindingInfo.getNumber() != 1){
            Collection<?> collectionObject = null;
            try {
                collectionObject = (Collection<?>) bindingInfo.getClazz().newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

            for(int i=0; i<bindingInfo.getNumber();i++){
                collectionObject.add(buildObject(bindingInfo.getMultipleBindingInfo()));
            }
            return (T) collectionObject;
        } else {
            return buildObject(bindingInfo);
        }
    }

    private <T> T buildObject(BindingInfo bindingInfo){
        logger.log(Level.INFO, "Building object: " + bindingInfo.getClazz().getSimpleName());
        ClassDependencies classDependencies = bindingInfo.getClassDependencies();
        ConstructorDependency constructorDependency = classDependencies.getConstructorDependency();
        Constructor constructor = constructorDependency.getConstructor();
        List<Object> arguments = new LinkedList<>();

        for(BindingInfo argumentBindingInfo:constructorDependency.getArguments()){
            arguments.add(argumentBindingInfo.getScope().getInstance(this));
        }
        T outputObject = null;
        try {
            outputObject = (T)constructor.newInstance(arguments.toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        injectDependencies(outputObject, bindingInfo);
        return outputObject;
    }

    private <T> void injectDependencies(T outputObject, BindingInfo bindingInfo) {
        logger.log(Level.INFO, "Injecting dependencies for: " + bindingInfo.getClazz().getSimpleName());
        ClassDependencies classDependencies = bindingInfo.getClassDependencies();
        List<MethodDependency> methodDependencies = classDependencies.getMethodsToInject();
        injectMethods(outputObject, methodDependencies);

        List<FieldDependency> fieldDependencies = classDependencies.getFieldsToInject();
        injectFields(outputObject, fieldDependencies);
    }

    private <T> void injectMethods(T outputObject, List<MethodDependency> methodDependencies){
        for(MethodDependency methodDependency:methodDependencies){
            methodDependency.getMethod().setAccessible(true);
            List<Object> arguments = new LinkedList<>();
            if(methodDependency.canInvoke()){
                for(BindingInfo argumentBindingInfo:methodDependency.getArguments()){
                    if(methodDependency.isOptional()){
                        try{
                            arguments.add(argumentBindingInfo.getScope().getInstance(this));
                        } catch (Throwable throwable){
                            methodDependency.setCanInvoke(false);                        }
                    } else {
                        arguments.add(argumentBindingInfo.getScope().getInstance(this));
                    }
                }

                if(methodDependency.canInvoke()){
                    try {
                        methodDependency.getMethod().invoke(outputObject, arguments.toArray());
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private <T> void injectFields(T outputObject, List<FieldDependency> fieldDependencies){
        for(FieldDependency fieldDependency:fieldDependencies){
            fieldDependency.getField().setAccessible(true);
            Object createdObject = null;

            if(fieldDependency.canInvoke()){
                if(fieldDependency.isOptional()){
                    try{
                        createdObject = fieldDependency.getArgument().getScope().getInstance(this);
                    } catch (Throwable throwable){
                        fieldDependency.setCanInvoke(false);                        }
                } else {
                    createdObject = fieldDependency.getArgument().getScope().getInstance(this);
                }

                if(fieldDependency.canInvoke()){
                    try {
                        fieldDependency.getField().set(outputObject, createdObject);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
