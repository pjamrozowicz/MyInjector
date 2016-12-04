package MethodDependencyInjectionTest;

import Common.Warrior;
import myinjector.Creator;

public class MethodDependencyInjection {
    public static Warrior getWarriorWithGunAndSilencer(){
        Creator creator = new Creator(new BindingService());
        return creator.resolve(Warrior.class);
    }
}
