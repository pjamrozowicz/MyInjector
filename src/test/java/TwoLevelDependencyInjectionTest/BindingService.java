package TwoLevelDependencyInjectionTest;

import Common.IWeapon;
import myinjector.AbstractSettings;


public class BindingService extends AbstractSettings {

    public void load() {
        addBinding(IWeapon.class, Sword.class);
    }
}
