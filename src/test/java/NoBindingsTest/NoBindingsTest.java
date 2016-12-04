package NoBindingsTest;


import Common.Sword;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class NoBindingsTest {
    @Test
    public void testGetSwordFromBindingService(){
        Sword sword = NoBindings.getSwordFromBindingService();
        assertThat(sword, instanceOf(Sword.class));
    }
}
