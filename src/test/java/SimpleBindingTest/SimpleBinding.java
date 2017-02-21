package SimpleBindingTest;

import Common.IWeapon;
import myinjector.IMyInjector;
import myinjector.InjectorFactory;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class SimpleBinding {

    @Test
    public void getIWeapon(){
        IMyInjector myInjector = InjectorFactory.getInjector("DefaultInjector", new BindingService());
        Sword sword = myInjector.get(IWeapon.class);
        assertThat(sword, instanceOf(Sword.class));
    }
}
