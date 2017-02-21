package myinjector;

import myinjector.Annotations.*;
import myinjector.Dependencies.*;
import myinjector.Exceptions.CycleDependencyException;

import java.lang.reflect.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultInjector implements IMyInjector {

    private AbstractSettings settings;
    private ComponentBuilder componentBuilder;
    private static final Logger logger = Logger.getLogger("myinjector");

    public DefaultInjector(AbstractSettings settings){
        this.settings = settings;
        this.componentBuilder = new ComponentBuilder();
        logger.setLevel(Level.OFF);
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "%1$tF %1$tT \t %5$s%6$s%n");
    }

    public void turnOnLogger(){
        logger.setLevel(Level.INFO);
    }

    public void turnOffLogger(){
        logger.setLevel(Level.OFF);
    }

    public <T> T get(Class clazz){
        BindingInfo bindingInfo = checkBinding(clazz);
        return bindingInfo.getScope().getInstance(componentBuilder);
    }

    private BindingInfo checkBinding(Class clazz){
        BindingInfo bindingInfo = settings.getBindingInfo(clazz);
        return updateBindingInfo(clazz, bindingInfo);
    }

    private BindingInfo checkBinding(Class clazz, String name){
        BindingInfo bindingInfo = settings.getNamedBindingInfo(clazz, name);
        return updateBindingInfo(clazz,bindingInfo);
    }

    private BindingInfo updateBindingInfo(Class clazz, BindingInfo bindingInfo){
        if(bindingInfo == null){
            bindingInfo = settings.addBinding(clazz, clazz);
        }

        if(!bindingInfo.getClazz().isPrimitive()
                && bindingInfo.getClassDependencies() == null
                && !bindingInfo.getClazz().isArray()
                && !Collection.class.isAssignableFrom(bindingInfo.getClazz())
                && !String.class.isAssignableFrom(bindingInfo.getClazz())){
            resolveClassAnnotation(bindingInfo);
            resolveDependencies(bindingInfo);
        }
        return bindingInfo;
    }

    private void resolveClassAnnotation(BindingInfo bindingInfo){
        if(isAnnotatedWith(Singleton.class, bindingInfo.getClazz())){
            bindingInfo.setSingleton();
        }
    }

    private void resolveDependencies(BindingInfo bindingInfo) {
        ClassDependencies classDependencies = new ClassDependencies(bindingInfo.getClazz());
        bindingInfo.setClassDependencies(classDependencies);

        Constructor constructor = classDependencies.getConstructorDependency().getConstructor();
        classDependencies.getConstructorDependency().setArguments(getArguments(constructor));

        List<MethodDependency> methodDependencyList = classDependencies.getMethodsToInject();
        for(MethodDependency methodDependency:methodDependencyList){
            if(methodDependency.isOptional()){
                try {
                    methodDependency.setArguments(getArguments(methodDependency.getMethod()));
                } catch (Throwable throwable){
                    methodDependency.setCanInvoke(false);
                }
            } else {
                methodDependency.setArguments(getArguments(methodDependency.getMethod()));
            }
        }

        List<FieldDependency> fieldDependenciesList = classDependencies.getFieldsToInject();
        for(FieldDependency fieldDependency:fieldDependenciesList){
            Field field = fieldDependency.getField();
            if(fieldDependency.isOptional()){
                try {
                    fieldDependency.setArgument(checkAnnotationsAndReturnBinding(field));
                } catch (Throwable throwable){
                    fieldDependency.setCanInvoke(false);
                }
            } else {
                fieldDependency.setArgument(checkAnnotationsAndReturnBinding(field));
            }
        }

        if(isThereCycleDependency(bindingInfo, new HashMap<>())){
            throw new CycleDependencyException("There is a cycle dependency in class " + bindingInfo.getClazz());
        }
    }

    private List<BindingInfo> getArguments(Executable executable){
        List<BindingInfo> bindingInfoList = new LinkedList<>();
        for(Parameter parameter:executable.getParameters()){
            bindingInfoList.add(checkAnnotationsAndReturnBinding(parameter));
        }
        return bindingInfoList;
    }

    private BindingInfo checkAnnotationsAndReturnBinding(Parameter parameter){
        BindingInfo bindingInfo;
        if(isAnnotatedWith(Named.class, parameter)){
            bindingInfo = checkBinding(parameter.getType(), parameter.getAnnotation(Named.class).name());
        } else {
            bindingInfo = checkBinding(parameter.getType());
        }

        if(isAnnotatedWith(Multiple.class, parameter)){
            bindingInfo.setMultiple(parameter.getAnnotation(Multiple.class).number());
            Type collectionType = parameter.getParameterizedType();
            Type elementType = ((ParameterizedType) collectionType).getActualTypeArguments()[0];
            bindingInfo.setMultipleBindingInfo(checkBinding((Class)elementType));
        }

        return bindingInfo;
    }

    private BindingInfo checkAnnotationsAndReturnBinding(Field field){
        BindingInfo bindingInfo;
        if(isAnnotatedWith(Named.class, field)){
            bindingInfo = checkBinding(field.getType(), field.getAnnotation(Named.class).name());
        } else {
            bindingInfo = checkBinding(field.getType());
        }

        if(isAnnotatedWith(Multiple.class, field)){
            bindingInfo.setMultiple(field.getAnnotation(Multiple.class).number());
            Type collectionType = field.getGenericType();
            Type elementType = ((ParameterizedType) collectionType).getActualTypeArguments()[0];
            bindingInfo.setMultipleBindingInfo(checkBinding((Class)elementType));
        }

        return bindingInfo;
    }

    private boolean isAnnotatedWith(Class annotationClass, AnnotatedElement annotatedElement){
        return annotatedElement.getAnnotation(annotationClass) != null;
    }

    private boolean isThereCycleDependency(BindingInfo bindingInfo, Map<Class, Integer> dependencyMap){
        bindingInfo = selectCorrectBindingInfo(bindingInfo);

        if(bindingInfo.getClassDependencies() == null){
            return false;
        }

        if(dependencyMap.containsKey(bindingInfo.getClazz())){
            return true;
        } else {
            List<BindingInfo> argumentsList = bindingInfo.getClassDependencies().getConstructorDependency().getArguments();
            if(argumentsList == null){
                return false;
            }
            dependencyMap.put(bindingInfo.getClazz(), 1);
            for(BindingInfo argumentBindingInfo:argumentsList){
                if(isThereCycleDependency(argumentBindingInfo, dependencyMap)){
                    return true;
                }
            }

            return false;
        }
    }

    private BindingInfo selectCorrectBindingInfo(BindingInfo bindingInfo){
        return (bindingInfo.getMultipleBindingInfo() != null) ? bindingInfo.getMultipleBindingInfo() : bindingInfo;
    }


}
