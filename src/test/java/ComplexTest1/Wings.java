package ComplexTest1;


import myinjector.Annotations.Multiple;

import java.util.ArrayList;

public class Wings {
    private ArrayList<Wing> wings;

    public ArrayList<Wing> getWings() {
        return wings;
    }

    public void setWings(@Multiple(number = 2) ArrayList<Wing> wings) {
        this.wings = wings;
    }
}
