package Examples;


public class Sword implements IWeapon {
    public void hit(String target){
        System.out.println(String.format("Cut %s in half", target));
    }
}
