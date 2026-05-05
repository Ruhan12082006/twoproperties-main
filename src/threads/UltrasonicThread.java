package threads;

import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.lcd.LCD;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import lejos.hardware.Button;
import data.Robot;

public class UltrasonicThread extends Thread {

    EV3UltrasonicSensor ultrasonicSensor;

    public UltrasonicThread() {
        ultrasonicSensor = new EV3UltrasonicSensor(SensorPort.S2);
    }

    @Override
    public void run() {
        SampleProvider distance = ultrasonicSensor.getDistanceMode();
        float[] sample = new float[distance.sampleSize()];

        while (!Button.ESCAPE.isDown()) {
            distance.fetchSample(sample, 0);
            float currentDistance = sample[0];
            Robot.setObstacleDetected(currentDistance < 0.20f);
            LCD.drawString("Dist: " + String.format("%.2f", currentDistance) + "m  ", 0, 3);
            Delay.msDelay(50);
        }
        ultrasonicSensor.close();
    }
}