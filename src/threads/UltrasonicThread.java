package threads;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.lcd.LCD;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import lejos.hardware.Button;
import java.net.HttpURLConnection;
import java.net.URL;
import data.Robot;
public class UltrasonicThread extends Thread {
EV3UltrasonicSensor ultrasonicSensor;
private boolean wasDetected = false;
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
boolean detected = currentDistance < 0.20f;
Robot.setObstacleDetected(detected);
LCD.drawString("Dist: " + String.format("%.2f", currentDistance) + "m  ", 0, 3);
if (detected && !wasDetected) {
notifyServer();
            }
wasDetected = detected;
Delay.msDelay(50);
        }
ultrasonicSensor.close();
    }
private void notifyServer() {
try {
URL url = new URL("http://172.31.160.87:8080/legorest2/rest/lego/obstacledetected");
HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setRequestMethod("GET");
conn.setConnectTimeout(500);
conn.setReadTimeout(500);
conn.getInputStream().close();
conn.disconnect();
        }
catch (Exception e) {
System.out.println("Could not notify server");
        }
    }
}