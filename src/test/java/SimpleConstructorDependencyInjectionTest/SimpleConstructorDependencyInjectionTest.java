package SimpleConstructorDependencyInjectionTest;

import Common.Sword;
import Common.Warrior;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;

public class SimpleConstructorDependencyInjectionTest {
    @Test
    public void testConstructorDependencyInjection(){
        Warrior warrior = SimpleConstructorDependencyInjection.constructorDependencyInjection();
        assertThat(warrior.getWeapon(), instanceOf(Sword.class));
    }
}
