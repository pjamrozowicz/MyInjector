package Examples.SimpleConstructorDependencyInjection;

import Examples.IWeapon;
import Examples.Sword;
import myinjector.AbstractInjector;


public class BindingService extends AbstractInjector{

    public void load() {
        addBinding(IWeapon.class, Sword.class);
    }
}
