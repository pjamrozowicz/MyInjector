package NamedBindingTest;


import myinjector.MyInjector;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class NamedBinding {
    @Test
    public void getNamedBindingKatana(){
        MyInjector myInjector = new MyInjector(new BindingService());
        Warrior warrior = myInjector.get(Warrior.class);
        assertThat(warrior.getWeapon(), instanceOf(Katana.class));
    }
}
