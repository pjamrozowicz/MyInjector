package OptionalAnnotationTest;

import Common.IWeapon;
import myinjector.Annotations.Inject;
import myinjector.Annotations.Optional;

public class Sword implements IWeapon {

    public String swordName;

    public Sword(){

    }
    public void hit(String target){
        System.out.println(String.format("Cut %s in half", target));
    }

    @Inject
    @Optional
    public void setSwordName(String name){
        this.swordName = name;
    }
}
