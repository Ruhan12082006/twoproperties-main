package threads;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import data.*;

public class ReadData implements Runnable {
    URL url = null;
    HttpURLConnection conn = null;
    InputStreamReader isr = null;
    BufferedReader br = null;
    String s = null;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                url = new URL("http://10.208.93.41:8080/rest/lego/getvalues");
                conn = (HttpURLConnection) url.openConnection();
                InputStream is = null;
                try {
                    is = conn.getInputStream();
                } catch (Exception e) {
                    System.out.println("Cannot get InputStream!");
                    e.printStackTrace();
                }
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);
                while ((s = br.readLine()) != null) {
                    String[] values = s.split("#");
                    Robot.setId(values[0]);
                    Robot.setRun(values[1]);
                    Robot.setSpeed(values[2]);
                    Robot.setTurn(values[3]);
                }
                br.close();
                isr.close();
                is.close();
                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Some problem!");
            }
        }
    }
}