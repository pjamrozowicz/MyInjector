package MultiThreadTest;


import myinjector.IMyInjector;

public class BuildAirportThread extends Thread {
    IMyInjector myInjector;
    private Airport airport = null;

    public BuildAirportThread(IMyInjector myInjector){
        this.myInjector = myInjector;
    }

    public void run(){
        airport = myInjector.get(Airport.class);
    }

    public Airport getAirport() {
        return airport;
    }
}
