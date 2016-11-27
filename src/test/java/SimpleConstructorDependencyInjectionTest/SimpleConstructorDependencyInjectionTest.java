package SimpleConstructorDependencyInjectionTest;

import Examples.SimpleConstructorDependencyInjection.SimpleConstructorDependencyInjection;
import Examples.Warrior;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;

public class SimpleConstructorDependencyInjectionTest {
    @Test
    public void testConstructorDependencyInjection(){
        Warrior warrior = SimpleConstructorDependencyInjection.constructorDependencyInjection();
        assertThat(warrior, instanceOf(Warrior.class));
    }
}
