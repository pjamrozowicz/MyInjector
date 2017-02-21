package DefinedSingletonTest;


import Common.IWeapon;
import myinjector.IMyInjector;
import myinjector.InjectorFactory;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public class DefinedSingleton {
    @Test
    public void getSingletonSword(){
        IMyInjector myInjector = InjectorFactory.getInjector("DefaultInjector", new BindingService());
        Sword sword1 = myInjector.get(IWeapon.class);
        Sword sword2 = myInjector.get(IWeapon.class);
        assertSame(sword1, sword2);
    }
}
