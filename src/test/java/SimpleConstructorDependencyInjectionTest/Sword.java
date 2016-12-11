package SimpleConstructorDependencyInjectionTest;


import Common.IWeapon;

public class Sword implements IWeapon {

    public Sword(){

    }
    public void hit(String target){
        System.out.println(String.format("Cut %s in half", target));
    }
}
