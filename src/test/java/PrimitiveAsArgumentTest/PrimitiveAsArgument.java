package PrimitiveAsArgumentTest;


import myinjector.IMyInjector;
import myinjector.InjectorFactory;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class PrimitiveAsArgument {
    @Test
    public void testStringAsArgument(){
        IMyInjector myInjector = InjectorFactory.getInjector("DefaultInjector", new BindingService());
        DatabaseConnector databaseConnector = myInjector.get(DatabaseConnector.class);
        assertThat(databaseConnector, instanceOf(DatabaseConnector.class));
    }
}
