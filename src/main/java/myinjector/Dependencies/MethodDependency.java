package myinjector.Dependencies;

import myinjector.BindingInfo;

import java.lang.reflect.Method;
import java.util.List;

public class MethodDependency {
    private Method method;
    private List<BindingInfo> arguments;
    private boolean isOptional = false;
    private boolean canInvoke = true;

    public MethodDependency(Method method) {
        this.method = method;
    }

    public List<BindingInfo> getArguments() {
        return arguments;
    }

    public void setArguments(List<BindingInfo> arguments) {
        this.arguments = arguments;
    }

    public Method getMethod() {
        return method;
    }

    public boolean isOptional() {
        return isOptional;
    }

    public void setOptional(boolean optional) {
        isOptional = optional;
    }

    public boolean canInvoke() {
        return canInvoke;
    }

    public void setCanInvoke(boolean canInvoke) {
        this.canInvoke = canInvoke;
    }
}
