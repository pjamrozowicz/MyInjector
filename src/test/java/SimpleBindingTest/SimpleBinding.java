package SimpleBindingTest;

import Common.IWeapon;
import Common.Sword;
import myinjector.Creator;

public class SimpleBinding {
    public static Sword getSwordFromBindingServiceUsingBindings(){
        Creator creator = new Creator(new BindingService());
        return creator.resolve(IWeapon.class);
    }
}
