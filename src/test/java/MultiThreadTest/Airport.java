package MultiThreadTest;


import myinjector.Annotations.Inject;
import myinjector.Annotations.Named;

public class Airport {
    private String name;
    private int capacity;
    private Plane firstPlane;
    private Plane secondPlane;

    public Airport(@Named(name = "AO") String name, @Named(name = "Boeing 123") Plane firstPlane, @Named(name = "Boeing 797") Plane secondPlane){
        this.name = name;
        this.firstPlane = firstPlane;
        this.secondPlane = secondPlane;
    }

    public String getName() {
        return name;
    }

    public Plane getFirstPlane() {
        return firstPlane;
    }

    public Plane getSecondPlane() {
        return secondPlane;
    }

    public int getCapacity() {
        return capacity;
    }

    @Inject
    public void setCapacity(@Named(name = "capacity1") int capacity) {
        this.capacity = capacity;
    }
}
