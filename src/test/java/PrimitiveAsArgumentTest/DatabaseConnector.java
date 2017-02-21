package PrimitiveAsArgumentTest;


import myinjector.Annotations.Named;

public class DatabaseConnector {
    private final IDriver driver;
    private String url;

    public DatabaseConnector(@Named(name = "URL") String url, IDriver driver){
        this.url = url;
        this.driver = driver;
    }
}
