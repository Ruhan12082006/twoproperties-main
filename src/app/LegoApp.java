package app;
import threads.*;

public class LegoApp {
    public static void main(String[] args) {
        RunLego runLego = new RunLego();
        ReadData readData = new ReadData();
        UltrasonicThread ultrasonicThread = new UltrasonicThread();

        System.out.println("Run in Threads");

        Thread runLegoThread = new Thread(runLego);
        Thread readDataThread = new Thread(readData);

        ultrasonicThread.setDaemon(true);
        ultrasonicThread.start();
        runLegoThread.start();
        readDataThread.start();
    }
}