package FieldDependencyInjectionTest;

import Common.IWeapon;
import myinjector.Annotations.Inject;

public class Gun implements IWeapon {

    @Inject
    private GunSilencer gunSilencer;

    @Inject
    public Gun(){

    }

    public void hit(String target) {
        System.out.println("Pif paf");
    }

    public GunSilencer getSilencer(){
        return this.gunSilencer;
    }
}
