package ComplexTest1;

import myinjector.Annotations.Inject;
import myinjector.Annotations.Multiple;

import java.util.ArrayList;
import java.util.LinkedList;


public abstract class Plane {

    @Inject
    private Wings wings;
    @Inject
    @Multiple(number = 10)
    private ArrayList<CrewMember> crewMembers;
    @Inject
    @Multiple(number = 100)
    private LinkedList<Passenger> passengers;
    private Pilot pilot;

    public abstract void fly();

    @Inject
    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public Wings getWings() {
        return wings;
    }

    public ArrayList<CrewMember> getCrewMembers() {
        return crewMembers;
    }

    public LinkedList<Passenger> getPassengers() {
        return passengers;
    }
}
