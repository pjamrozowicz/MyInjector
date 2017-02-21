package MultiThreadTest;

import myinjector.IMyInjector;
import myinjector.InjectorFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class MultiThreadTest {
    Airport airport1;
    Airport airport2;
    BuildAirportThread thread1;
    BuildAirportThread thread2;

    @Before
    public void multiThread(){
        IMyInjector myInjector = InjectorFactory.getInjector("DefaultInjector", new BindingService());
        myInjector.turnOnLogger();
        BuildAirportThread thread1 = new BuildAirportThread(myInjector);
        BuildAirportThread thread2 = new BuildAirportThread(myInjector);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testAirport1(){
        airport1 = thread1.getAirport();
        assertThat(airport1, instanceOf(Airport.class));
    }

    @Test
    public void testAirport2(){
        airport2 = thread2.getAirport();
        assertThat(airport2, instanceOf(Airport.class));
    }

    @Test
    public void testOtherThings(){
        Plane boeing123_1 = airport1.getFirstPlane();
        Plane boeing123_2 = airport2.getFirstPlane();
        Pilot firstPilot_1 = boeing123_1.getPilot();
        Pilot firstPilot_2 = boeing123_2.getPilot();
        assertThat(firstPilot_1, instanceOf(Pilot.class));
        assertThat(firstPilot_2, instanceOf(Pilot.class));
        Plane boeing797_1 = airport1.getSecondPlane();
        Plane boeing797_2 = airport2.getSecondPlane();
        Pilot secondPilot_1 = boeing797_1.getPilot();
        Pilot secondPilot_2 = boeing797_2.getPilot();
        assertSame(firstPilot_1, secondPilot_1);
        assertSame(firstPilot_2, secondPilot_2);
        assertNotEquals(firstPilot_1, firstPilot_2);
        ArrayList<CrewMember> crewMembers_1 = boeing123_1.getCrewMembers();
        ArrayList<CrewMember> crewMembers_2 = boeing123_2.getCrewMembers();
        assertEquals(crewMembers_1.size(), 10);
        assertEquals(crewMembers_2.size(), 10);
        assertThat(crewMembers_1.get(9), instanceOf(CrewMember.class));
        assertThat(crewMembers_2.get(9), instanceOf(CrewMember.class));
    }

}
