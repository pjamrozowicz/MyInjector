package Examples.SimpleConstructorDependencyInjection;

import Examples.Warrior;

public class SimpleConstructorDependencyInjection {
    public static Warrior constructorDependencyInjection(){
        BindingService bindingService = new BindingService();
        Warrior warrior = bindingService.getInstanceOf(Warrior.class);
        return warrior;
    }
}
