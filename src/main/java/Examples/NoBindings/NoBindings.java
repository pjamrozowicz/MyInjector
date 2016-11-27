package Examples.NoBindings;

import Examples.Sword;

public class NoBindings {

    public static Sword getSwordFromBindingService(){
        BindingService bindingService = new BindingService();
        return bindingService.getInstanceOf(Sword.class);
    }
}
