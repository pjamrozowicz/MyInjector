package NoBindingsTest;

import myinjector.MyInjector;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class NoBindings {

    @Test
    public void testGetSword(){
        MyInjector myInjector = new MyInjector(new BindingService());
        Sword sword = myInjector.get(Sword.class);
        assertThat(sword, instanceOf(Sword.class));
    }
}
