package NamedBindingTest;


import myinjector.IMyInjector;
import myinjector.InjectorFactory;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class NamedBinding {
    @Test
    public void getNamedBindingKatana(){
        IMyInjector myInjector = InjectorFactory.getInjector("DefaultInjector", new BindingService());
        Warrior warrior = myInjector.get(Warrior.class);
        assertThat(warrior.getWeapon(), instanceOf(Katana.class));
    }
}
