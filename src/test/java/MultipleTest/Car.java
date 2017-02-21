package MultipleTest;

import myinjector.Annotations.Inject;
import myinjector.Annotations.Multiple;

import java.util.LinkedList;
import java.util.List;

public class Car {
    private List<Tire> tires;

    @Inject
    public Car(@Multiple(number=4)LinkedList<Tire> tires){
        this.tires = tires;
    }

    public List<Tire> getTires() {
        return tires;
    }
}
