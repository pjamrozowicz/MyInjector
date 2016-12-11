package myinjector;

import myinjector.Scopes.IScope;
import myinjector.Scopes.Prototype;
import myinjector.Scopes.Singleton;

public class BindingInfo {
    private Class clazz;
    private String name;
    private IScope scope;


    public BindingInfo(Class clazz){
        this.clazz = clazz;
        this.scope = new Prototype();
    }

    public BindingInfo setSingleton(){
        this.scope = new Singleton();
        return this;
    }

    public BindingInfo setName(String name){
        this.name = name;
        return this;
    }

    public Class getClazz() {
        return clazz;
    }

    public String getName() {
        return name;
    }

    public IScope getScope() {
        return scope;
    }
}
