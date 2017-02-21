package MultiThreadTest;


import myinjector.AbstractSettings;

public class BindingService extends AbstractSettings {

    public void load() {
        addBinding(Plane.class, Boeing123.class).setBindingName("Boeing 123");
        addBinding(Plane.class, Boeing797.class).setBindingName("Boeing 797");
        addBinding(String.class, String.class).setBindingName("AO").toInstance("Aiport One");
        addBinding(int.class, int.class).setBindingName("capacity1").toInstance(2);
    }
}
