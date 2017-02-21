package MultipleTest;

import myinjector.AbstractSettings;

public class BindingService extends AbstractSettings {

    public void load() {
        addBinding(Car.class, Car.class);
    }
}
