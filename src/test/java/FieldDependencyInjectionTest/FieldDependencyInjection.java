package FieldDependencyInjectionTest;

import Common.Warrior;
import myinjector.MyInjector;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class FieldDependencyInjection {
    @Test
    public void getWarriorWithGunAndSilencer() {
        MyInjector myInjector = new MyInjector(new BindingService());
        Warrior warrior = myInjector.get(Warrior.class);
        Gun gun = (Gun) warrior.getWeapon();
        assertThat(warrior.getWeapon(), instanceOf(Gun.class));
        assertThat(gun.getSilencer(), instanceOf(GunSilencer.class));
    }
}
