package myinjector.Dependencies;

import myinjector.BindingInfo;

import java.lang.reflect.Constructor;
import java.util.List;

public class ConstructorDependency {
    private Constructor constructor;
    private List<BindingInfo> arguments;

    public ConstructorDependency(Constructor constructor){
        this.constructor = constructor;
    }

    public Constructor getConstructor() {
        return constructor;
    }

    public List<BindingInfo> getArguments() {
        return arguments;
    }

    public void setArguments(List<BindingInfo> arguments) {
        this.arguments = arguments;
    }
}
