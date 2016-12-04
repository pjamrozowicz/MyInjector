package SimpleBindingTest;

import Common.Sword;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;

public class SimpleBindingTest {
    @Test
    public void testGetSwordFromBindingService(){
        Sword sword = SimpleBinding.getSwordFromBindingServiceUsingBindings();
        assertThat(sword, instanceOf(Sword.class));
    }
}
