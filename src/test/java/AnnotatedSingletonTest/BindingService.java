package AnnotatedSingletonTest;

import myinjector.AbstractSettings;


public class BindingService extends AbstractSettings {

    public void load() {
        addBinding(Sword.class, Sword.class);
    }
}
