package TwoLevelDependencyInjectionTest;

import Common.Sword;
import Common.Warrior;
import Common.WarriorHouse;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class TwoLevelDependencyInjectionTest {
    @Test
    public void testTwoLevelDependencyInjection(){
        WarriorHouse warriorHouse = TwoLevelDependencyInjection.twoLevelDependencyInjection();
        assertThat(warriorHouse.getWarrior(), instanceOf(Warrior.class));
        assertThat(warriorHouse.getWarrior().getWeapon(), instanceOf(Sword.class));
    }
}
