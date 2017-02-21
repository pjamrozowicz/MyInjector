package myinjector.Dependencies;


import myinjector.Annotations.Inject;
import myinjector.Annotations.Optional;
import myinjector.Dependencies.ConstructorDependency;
import myinjector.Dependencies.FieldDependency;
import myinjector.Dependencies.MethodDependency;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


public class ClassDependencies {
    private ConstructorDependency constructor;
    private List<MethodDependency> methodsToInject;
    private List<FieldDependency> fieldsToInject;

    public ClassDependencies(Class clazz){
        this.constructor = new ConstructorDependency(findConstructor(clazz));
        this.methodsToInject = new LinkedList<>();
        this.fieldsToInject = new LinkedList<>();
        findMethodsAndFields(clazz);
    }

    private Constructor findConstructor(Class clazz) {
        Constructor[] allConstructors = clazz.getConstructors();
        if(allConstructors.length == 0){
            Constructor constructor = null;
            try {
                constructor = clazz.getConstructor();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
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

    private void findMethodsAndFields(Class clazz) {
        List<Class> classes = getAllExtendedOrImplementedTypes(clazz);
        for(Class clazzFromList:classes){
            checkClassMethods(clazzFromList);
            checkClassFields(clazzFromList);
        }
    }

    private void checkClassMethods(Class clazz){
        for(Method method : clazz.getDeclaredMethods()){
            if(isAnnotatedWithInjected(method)){
                MethodDependency foundMethod = new MethodDependency(method);
                if(isAnnotatedWithOptional(method)){
                    foundMethod.setOptional(true);
                }
                methodsToInject.add(foundMethod);
            }
        }
    }

    private void checkClassFields(Class clazz){
        for(Field field : clazz.getDeclaredFields()){
            if(isAnnotatedWithInjected(field)){
                FieldDependency foundField = new FieldDependency(field);
                if(isAnnotatedWithOptional(field)){
                    foundField.setOptional(true);
                }
                fieldsToInject.add(foundField);
            }
        }
    }

    private boolean isAnnotatedWithInjected(AnnotatedElement annotatedElementn){
        return annotatedElementn.getAnnotation(Inject.class) != null;
    }

    private boolean isAnnotatedWithOptional(AnnotatedElement annotatedElementn){
        return annotatedElementn.getAnnotation(Optional.class) != null;
    }

    public List<Class> getAllExtendedOrImplementedTypes(Class clazz) {
        List<Class> result = new LinkedList<>();

        do {
            result.add(clazz);

            Class[] interfaces = clazz.getInterfaces();
            if (interfaces.length > 0) {
                result.addAll(Arrays.asList(interfaces));

                for (Class<?> interfaze : interfaces) {
                    result.addAll(getAllExtendedOrImplementedTypes(interfaze));
                }
            }

            Class<?> superClass = clazz.getSuperclass();

            if (superClass == null) {
                break;
            }

            clazz = superClass;
        } while (!"java.lang.Object".equals(clazz.getCanonicalName()));

        return result;
    }

    public List<FieldDependency> getFieldsToInject(){
        return fieldsToInject;
    }

    public ConstructorDependency getConstructorDependency() {
        return constructor;
    }

    public List<MethodDependency> getMethodsToInject(){
        return methodsToInject;
    }

}
