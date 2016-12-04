package MethodDependencyInjectionTest;

import Common.Warrior;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;

public class MethodDependencyInjectionTest {
    @Test
    public void testGetWarriorWithGunAndSilencer(){
        Warrior warrior = MethodDependencyInjection.getWarriorWithGunAndSilencer();
        assertThat(warrior.getWeapon(), instanceOf(Gun.class));
        Gun gun = (Gun) warrior.getWeapon();
        assertThat(gun.getSilencer(), instanceOf(GunSilencer.class));
    }
}
