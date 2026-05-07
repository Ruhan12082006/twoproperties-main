package threads;

import data.Robot;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import java.net.HttpURLConnection;
import java.net.URL;

public class RunLego implements Runnable {
    UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
    UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);

    @Override
    public void run() {
        while (!Button.ESCAPE.isDown()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (Robot.getRun() == 1) {
                if (Robot.isObstacleDetected()) {
                    Robot.incrementObstacleCount();
                    sendObstacleCount();
                    // Stop
                    motorA.setPower(0);
                    motorB.setPower(0);
                    LCD.drawString("Obstacle! " + Robot.getObstacleCount(), 0, 0);
                    Delay.msDelay(500);

                    // Turn around 180 degrees
                    motorA.setPower(75);
                    motorB.setPower(-75);
                    Delay.msDelay(1000);

                    motorA.setPower(0);
                    motorB.setPower(0);

                } else {
                    motorA.setPower(Robot.turnRight());
                    motorB.setPower(Robot.turnLeft());
                }
            } else {
                motorA.setPower(0);
                motorB.setPower(0);
            }
        }
    }

    private void sendObstacleCount() {
        try {
            URL url = new URL("http://172.31.161.60:8080/legorest2/rest/lego/obstacledetected");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.getResponseCode();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}