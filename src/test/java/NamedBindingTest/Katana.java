package NamedBindingTest;

import Common.IWeapon;

public class Katana implements IWeapon{

    public Katana(){

    }

    public void hit(String target){
        System.out.println(String.format("Cut %s in half with Katana", target));
    }
}
