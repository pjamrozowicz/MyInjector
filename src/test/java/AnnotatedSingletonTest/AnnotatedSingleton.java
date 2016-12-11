package AnnotatedSingletonTest;

import myinjector.MyInjector;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public class AnnotatedSingleton {
    @Test
    public void getOneSwordTwoTimes(){
        MyInjector myInjector = new MyInjector(new BindingService());
        Sword sword1 = myInjector.get(Sword.class);
        Sword sword2 = myInjector.get(Sword.class);
        assertSame(sword1, sword2);
    }
}
