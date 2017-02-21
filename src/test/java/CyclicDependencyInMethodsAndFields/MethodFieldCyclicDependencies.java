package CyclicDependencyInMethodsAndFields;

import myinjector.IMyInjector;
import myinjector.InjectorFactory;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class MethodFieldCyclicDependencies {
    @Test(expected = StackOverflowError.class)
    public void testCyclicDependency(){
        IMyInjector myInjector = InjectorFactory.getInjector("", new BindingService());
        Computer computer = myInjector.get(Computer.class);
        assertThat(computer, instanceOf(Computer.class));
    }
}
