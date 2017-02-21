package ComplexTest1;

import myinjector.IMyInjector;
import myinjector.InjectorFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

public class ComplexTest {
    @Test
    public void complexTest(){
        IMyInjector myInjector = InjectorFactory.getInjector("DefaultInjector", new BindingService());
        Airport airport = myInjector.get(Airport.class);
        assertThat(airport, instanceOf(Airport.class));
        Plane boeing123 = airport.getFirstPlane();
        Pilot firstPilot = boeing123.getPilot();
        assertThat(firstPilot, instanceOf(Pilot.class));
        Plane boeing797 = airport.getSecondPlane();
        Pilot secondPilot = boeing797.getPilot();
        assertSame(firstPilot, secondPilot);
        ArrayList<CrewMember> crewMembers = boeing123.getCrewMembers();
        assertEquals(crewMembers.size(), 10);
        assertThat(crewMembers.get(9), instanceOf(CrewMember.class));
        LinkedList<Passenger> passengers = boeing123.getPassengers();
        assertEquals(passengers.size(), 100);
        assertThat(passengers.get(99), instanceOf(Passenger.class));
    }
}
