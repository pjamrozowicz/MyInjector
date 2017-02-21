package TwoLevelDependencyInjectionTest;


import Common.Warrior;
import Common.WarriorHouse;
import myinjector.IMyInjector;
import myinjector.InjectorFactory;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class TwoLevelDependencyInjection {
    @Test
    public void twoLevelDependencyInjection(){
        IMyInjector myInjector = InjectorFactory.getInjector("DefaultInjector", new BindingService());
        WarriorHouse warriorHouse = myInjector.get(WarriorHouse.class);
        assertThat(warriorHouse.getWarrior(), instanceOf(Warrior.class));
        assertThat(warriorHouse.getWarrior().getWeapon(), instanceOf(Sword.class));
    }

}
