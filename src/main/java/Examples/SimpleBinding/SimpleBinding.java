package Examples.SimpleBinding;

import Examples.IWeapon;
import Examples.Sword;

public class SimpleBinding {
    public static Sword getSwordFromBindingServiceUsingBindings(){
        BindingService bindingService = new BindingService();
        return bindingService.getInstanceOf(IWeapon.class);
    }
}
