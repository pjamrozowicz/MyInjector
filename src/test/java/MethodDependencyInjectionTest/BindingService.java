package MethodDependencyInjectionTest;

import Common.IWeapon;
import myinjector.AbstractSettings;


public class BindingService extends AbstractSettings {

    public void load() {
        addBinding(IWeapon.class, Gun.class);
    }
}
