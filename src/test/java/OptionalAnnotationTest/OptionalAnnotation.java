package OptionalAnnotationTest;

import myinjector.IMyInjector;
import myinjector.InjectorFactory;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class OptionalAnnotation {

    @Test
    public void testGetSword(){
        IMyInjector myInjector = InjectorFactory.getInjector("DefaultInjector", new BindingService());
        Sword sword = myInjector.get(Sword.class);
        assertThat(sword, instanceOf(Sword.class));
    }
}
