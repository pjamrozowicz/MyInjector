package SimpleBindingTest;

import Common.IWeapon;
import myinjector.MyInjector;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class SimpleBinding {

    @Test
    public void getIWeapon(){
        MyInjector myInjector = new MyInjector(new BindingService());
        Sword sword = myInjector.get(IWeapon.class);
        assertThat(sword, instanceOf(Sword.class));
    }
}
