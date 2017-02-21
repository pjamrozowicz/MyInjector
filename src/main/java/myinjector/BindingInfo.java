package myinjector;

import myinjector.Dependencies.ClassDependencies;
import myinjector.Scopes.IScope;
import myinjector.Scopes.Prototype;
import myinjector.Scopes.Singleton;


public class BindingInfo {
    private Class clazz;
    private ClassDependencies classDependencies;
    private String bindingName;
    private IScope scope;
    private Object instance;
    private int number = 1;
    private BindingInfo multipleBindingInfo;


    public BindingInfo(Class clazz){
        this.clazz = clazz;
        this.scope = new Prototype(this);
    }

    public BindingInfo setSingleton(){
        this.scope = new Singleton(this);
        return this;
    }

    public BindingInfo setBindingName(String bindingName){
        this.bindingName = bindingName;
        return this;
    }

    public BindingInfo toInstance(Object instance){
        this.instance = instance;
        return this;
    }

    public BindingInfo setMultiple(int number){
        this.number = number;
        return this;
    }

    public Class getClazz() {
        return clazz;
    }

    public String getBindingName() {
        return bindingName;
    }

    public IScope getScope() {
        return scope;
    }

    public void setClassDependencies(ClassDependencies classDependencies) {
        this.classDependencies = classDependencies;
    }

    public ClassDependencies getClassDependencies(){
        return classDependencies;
    }

    public Object getInstance() {
        return instance;
    }

    public int getNumber() {
        return number;
    }

    public BindingInfo getMultipleBindingInfo() {
        return multipleBindingInfo;
    }

    public void setMultipleBindingInfo(BindingInfo multipleBindingInfo) {
        this.multipleBindingInfo = multipleBindingInfo;
    }
}
