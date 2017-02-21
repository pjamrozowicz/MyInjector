package myinjector.Dependencies;

import myinjector.BindingInfo;
import java.lang.reflect.Field;

public class FieldDependency {
    private Field field;
    private BindingInfo argument;
    private boolean isOptional = false;
    private boolean canInvoke = true;

    public FieldDependency(Field field) {
        this.field = field;
    }

    public BindingInfo getArgument() {
        return argument;
    }

    public void setArgument(BindingInfo argument) {
        this.argument = argument;
    }

    public Field getField() {
        return field;
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
