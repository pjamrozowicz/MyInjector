package myinjector.Scopes;

import myinjector.BindingInfo;
import myinjector.ComponentBuilder;

public class Prototype implements IScope{

    private BindingInfo bindingInfo;

    public Prototype(BindingInfo bindingInfo){
        this.bindingInfo = bindingInfo;
    }

    public <T> T getInstance(ComponentBuilder componentBuilder) {
        return componentBuilder.createInstance(bindingInfo);
    }
}
