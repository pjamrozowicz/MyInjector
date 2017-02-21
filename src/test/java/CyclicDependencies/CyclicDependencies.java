package CyclicDependencies;


import myinjector.Exceptions.CycleDependencyException;
import myinjector.IMyInjector;
import myinjector.InjectorFactory;
import org.junit.Test;

public class CyclicDependencies {
    @Test(expected = CycleDependencyException.class)
    public void testCyclicDependency(){
        IMyInjector myInjector = InjectorFactory.getInjector("DeafultInjector", new BindingService());
        Home home = myInjector.get(Home.class);
    }
}
