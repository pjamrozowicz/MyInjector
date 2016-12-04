package TwoLevelDependencyInjectionTest;


import Common.WarriorHouse;
import myinjector.Creator;

public class TwoLevelDependencyInjection {
    public static WarriorHouse twoLevelDependencyInjection(){
        Creator creator = new Creator(new BindingService());
        return creator.resolve(WarriorHouse.class);
    }
}
