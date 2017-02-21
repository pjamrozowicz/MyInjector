package MethodDependencyInjectionTest;

import Common.IWeapon;
import myinjector.Annotations.Inject;

public class Gun implements IWeapon {

    private GunSilencer gunSilencer;

    public Gun(){

    }

    public void hit(String target) {
        System.out.println("Pif paf");
    }

    GunSilencer getSilencer(){
        return this.gunSilencer;
    }

    @Inject
    private void setGunSilencer(GunSilencer gunSilencer){
        this.gunSilencer = gunSilencer;
    }
}
