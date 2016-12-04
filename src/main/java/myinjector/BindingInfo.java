package myinjector;

public class BindingInfo {
    private Class clazz;
    private String name;
    private boolean isSingleton = false;


    public BindingInfo(Class clazz){
        this.clazz = clazz;
    }

    public BindingInfo setSingleton(){
        this.isSingleton = true;
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

    public boolean isSingleton(){
        return this.isSingleton;
    }
}
