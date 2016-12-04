package SimpleConstructorDependencyInjectionTest;

import Common.Warrior;
import myinjector.Creator;

public class SimpleConstructorDependencyInjection {
    public static Warrior constructorDependencyInjection(){
        Creator creator = new Creator(new BindingService());
        Warrior warrior = creator.resolve(Warrior.class);
        return warrior;
    }
}
