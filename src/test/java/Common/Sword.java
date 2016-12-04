package Common;


import myinjector.Annotations.Inject;

public class Sword implements IWeapon {

    @Inject
    public Sword(){

    }
    public void hit(String target){
        System.out.println(String.format("Cut %s in half", target));
    }
}
