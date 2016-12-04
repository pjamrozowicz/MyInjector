package FieldDependencyInjectionTest;


import Common.Warrior;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;

public class FieldDependencyInjectionTest {
    @Test
    public void testGetWarriorWithGunAndSilencer(){
        Warrior warrior = FieldDependencyInjection.getWarriorWithGunAndSilencer();
        assertThat(warrior.getWeapon(), instanceOf(Gun.class));
        Gun gun = (Gun) warrior.getWeapon();
        assertThat(gun.getSilencer(), instanceOf(GunSilencer.class));
    }
}
