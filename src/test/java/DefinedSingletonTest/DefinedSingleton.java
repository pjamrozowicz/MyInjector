package DefinedSingletonTest;


import Common.IWeapon;
import myinjector.MyInjector;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public class DefinedSingleton {
    @Test
    public void getSingletonSword(){
        MyInjector myInjector = new MyInjector(new BindingService());
        Sword sword1 = myInjector.get(IWeapon.class);
        Sword sword2 = myInjector.get(IWeapon.class);
        assertSame(sword1, sword2);
    }
}
