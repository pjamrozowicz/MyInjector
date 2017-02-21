package MultipleTest;


import myinjector.IMyInjector;
import myinjector.InjectorFactory;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class MultipleTest {

    @Test
    public void testMultiple(){
        IMyInjector myInjector = InjectorFactory.getInjector("DefaultInjector", new BindingService());
        Car car = myInjector.get(Car.class);
        assertThat(car.getTires().get(0), instanceOf(Tire.class));
        assertThat(car.getTires().get(1), instanceOf(Tire.class));
        assertThat(car.getTires().get(2), instanceOf(Tire.class));
        assertThat(car.getTires().get(3), instanceOf(Tire.class));
    }
}
