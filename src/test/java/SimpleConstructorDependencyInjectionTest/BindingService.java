package SimpleConstructorDependencyInjectionTest;

import Common.IWeapon;
import Common.Sword;
import myinjector.AbstractSettings;


public class BindingService extends AbstractSettings {

    public void load() {
        addBinding(IWeapon.class, Sword.class);
    }
}
