package Common;

import myinjector.Annotations.Inject;

public class Warrior {
    private IWeapon weapon;

    @Inject
    public Warrior(IWeapon weapon){
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
