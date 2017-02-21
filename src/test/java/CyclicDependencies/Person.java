package CyclicDependencies;


public class Person {
    private Home home;

    public Person(Home home){
        this.home = home;
    }

    public Home getHome() {
        return home;
    }
}
