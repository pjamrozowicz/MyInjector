package SimpleConstructorDependencyInjectionTest;

import Common.Warrior;
import myinjector.IMyInjector;
import myinjector.InjectorFactory;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class SimpleConstructorDependencyInjection {

    @Test
    public void constructorDependencyInjection(){
        IMyInjector myInjector = InjectorFactory.getInjector("DefaultInjector", new BindingService());
        Warrior warrior = myInjector.get(Warrior.class);
        assertThat(warrior.getWeapon(), instanceOf(Sword.class));
    }

}
