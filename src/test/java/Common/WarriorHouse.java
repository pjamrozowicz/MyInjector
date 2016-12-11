package Common;


import myinjector.Annotations.Inject;

public class WarriorHouse {
    private Warrior warrior;

    @Inject
    public WarriorHouse(Warrior warrior){
        this.warrior = warrior;
    }

    public Warrior getWarrior(){
        return warrior;
    }
}
