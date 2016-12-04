package myinjector;


import myinjector.Annotations.Inject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class RealCreator {
    public void checkForDependencies(Class clazz){
        checkForMethodDependencies(clazz);
        checkForFieldDependencies(clazz);
    }

    private void checkForMethodDependencies(Class clazz) {
        Method[] methods = clazz.getMethods();
        for(Method method : methods){
            if(method.getAnnotation(Inject.class) != null){
                // ogarnij argumenty, wywolaj funkcje z nimi
            }
        }
    }

    private void checkForFieldDependencies(Class clazz) {
        Field[] fields = clazz.getFields();
        for(Field field : fields){
            if(field.getAnnotation(Inject.class) != null){
                // wywolaj resolve'a na klasie pola i wstrzyknij je
            }
        }
    }
}
