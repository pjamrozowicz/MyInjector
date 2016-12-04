package FieldDependencyInjectionTest;

import Common.Warrior;
import myinjector.Creator;

public class FieldDependencyInjection {
    public static Warrior getWarriorWithGunAndSilencer(){
        Creator creator = new Creator(new BindingService());
        return creator.resolve(Warrior.class);
    }
}
