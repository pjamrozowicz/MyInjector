package PrimitiveAsArgumentTest;


public class BestDriver implements IDriver {
    @Override
    public void connect(String url) {
        System.out.println("Connected to " + url);
    }
}
