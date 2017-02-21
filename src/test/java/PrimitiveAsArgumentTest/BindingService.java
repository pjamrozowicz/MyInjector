package PrimitiveAsArgumentTest;

import myinjector.AbstractSettings;


public class BindingService extends AbstractSettings {

    public void load() {
        addBinding(DatabaseConnector.class, DatabaseConnector.class);
        addBinding(IDriver.class, BestDriver.class);
        addBinding(String.class, String.class).setBindingName("URL").toInstance("url://myurl");
    }
}
