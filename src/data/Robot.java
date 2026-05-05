package data;
public class Robot {
private static int id;
private static int speed;
private static int turn;
private static int run=1;
private static volatile boolean obstacleDetected=false;
public static boolean isObstacleDetected() {
return obstacleDetected;
    }
public static void setObstacleDetected(boolean detected) {
Robot.obstacleDetected = detected;
    }
public static int getId() {
return id;
    }
public static void setId(int id) {
Robot.id = id;
    }
public static void setId(String id) {
try {
Robot.id = Integer.parseInt(id);
        }
catch(Exception e) {
//id remains unchanged
        }
    }
public static int getSpeed() {
return speed;
    }
public static int turnRight() {//Slower Right Motor
if (turn<0) {
return speed+turn;
        }
else {
return speed;
        }
    }
public static int turnLeft() {//Slower Left motor
if (turn>0) {
return speed-turn;
        }
else {
return speed;
        }
    }
public static void setSpeed(int speed) {
Robot.speed = speed;
    }
public static void setSpeed(String speed) {
try {
Robot.speed = Integer.parseInt(speed);
        }
catch(Exception e) {
//speed remains unchanged
        }
    }
public static int getTurn() {
return turn;
    }
public static void setTurn(int turn) {
Robot.turn = turn;
    }
public static void setTurn(String turn) {
try {
Robot.turn = Integer.parseInt(turn);
        }
catch(Exception e) {
///turn remains unchanged
        }
    }
public static int getRun() {
return run;
    }
public static void setRun(int run) {
Robot.run = run;
    }
public static void setRun(String run) {
try {
Robot.run = Integer.parseInt(run);
        }
catch(Exception e) {
///run remains unchanged
        }
    }
}