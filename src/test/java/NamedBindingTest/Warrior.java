package NamedBindingTest;

import Common.IWeapon;
import myinjector.Annotations.Named;

public class Warrior {
    private IWeapon weapon;

    public Warrior(@Named(name="Katana") IWeapon weapon){
        this.weapon = weapon;
    }

    public void attack(String target)
    {
        this.weapon.hit(target);
    }

    public IWeapon getWeapon(){
        return weapon;
    }
}
