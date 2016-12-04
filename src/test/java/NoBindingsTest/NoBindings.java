package NoBindingsTest;

import Common.Sword;
import myinjector.Creator;

public class NoBindings {

    public static Sword getSwordFromBindingService(){
        Creator creator = new Creator(new BindingService());
        return creator.resolve(Sword.class);
    }
}
