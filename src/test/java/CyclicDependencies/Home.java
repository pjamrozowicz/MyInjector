package CyclicDependencies;


public class Home {

    private Person person;

    public Home(Person person){
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }
}
