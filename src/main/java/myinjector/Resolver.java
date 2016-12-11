package myinjector;


import myinjector.Annotations.Singleton;

import java.lang.annotation.Annotation;

class Resolver {

    private static Resolver instance = null;
    private AbstractSettings settings;

    private Resolver(AbstractSettings settings){
        this.settings = settings;
    }

    static Resolver getInstance(AbstractSettings settings) {
        if(instance == null) {
            instance = new Resolver(settings);
        }
        //return instance;
        return new Resolver(settings);
    }

    <T> T resolve(Class clazz){
        System.out.println("resolving class " + clazz.getName());
        BindingInfo bindingInfo = settings.getBindingInfo(clazz);
        return checkBindingInfoAndStartBulding(clazz, bindingInfo);
    }

    public <T> T resolveNamed(Class clazz, String name){
        System.out.println("resolving named class " + clazz.getName());
        BindingInfo bindingInfo = settings.getNamedBindingInfo(clazz, name);
        return checkBindingInfoAndStartBulding(clazz,bindingInfo);
    }

    public <T> T checkBindingInfoAndStartBulding(Class clazz, BindingInfo bindingInfo){
        if(bindingInfo == null){
            System.out.println("I didnt find binding for " + clazz.getName());
            bindingInfo = settings.addBinding(clazz, clazz);
            resolveClassAnnotation(bindingInfo);
        }
        System.out.println("Scope for this class is: " + bindingInfo.getScope().getClass().getName());
        return bindingInfo.getScope().getInstance(bindingInfo.getClazz(), settings);
    }

    private void resolveClassAnnotation(BindingInfo bindingInfo){
        if(bindingInfo.getClazz().getAnnotation(Singleton.class) != null){
            bindingInfo.setSingleton();
        }
    }
}
