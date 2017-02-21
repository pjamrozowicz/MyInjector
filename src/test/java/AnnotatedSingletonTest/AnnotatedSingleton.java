package AnnotatedSingletonTest;

import myinjector.IMyInjector;
import myinjector.InjectorFactory;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public class AnnotatedSingleton {
    @Test
    public void getOneSwordTwoTimes(){
        IMyInjector myInjector = InjectorFactory.getInjector("DefaultInjector", new BindingService());
        Sword sword1 = myInjector.get(Sword.class);
        Sword sword2 = myInjector.get(Sword.class);
        assertSame(sword1, sword2);
    }
}
