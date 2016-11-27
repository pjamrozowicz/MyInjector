package Examples;

public class Warrior {
    private IWeapon weapon;

    public Warrior(IWeapon weapon){
        this.weapon = weapon;
    }

    public void attack(String target)
    {
        this.weapon.hit(target);
    }
}
