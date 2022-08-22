package com.example.analysisagent;

import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.management.OperatingSystemMXBean;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class HelloController {

    @FXML
    public Label invisibleText;
    public TextField timesInput;
    public TextField minutesInput;

    public  Label startedText;

    public Label finishedText;


    static  String  fileName="C:\\Users\\Varol\\Desktop\\cpuUsage.txt";
    @FXML
    protected void RunButtonClick() throws FileNotFoundException {

        //"\\taiintprofile02.dmnint.intra\Folders\s19079\Desktop\doc\eventLog.txt"

        Advapi32Util.EventLogIterator iter = new Advapi32Util.EventLogIterator("Application");

        PrintWriter writer=new PrintWriter(fileName);
        PrintWriter writerDown=new PrintWriter("C:\\Users\\Varol\\Desktop\\eventLog.txt");


        while (iter.hasNext()) {
            Advapi32Util.EventLogRecord record = iter.next();
            /*
            System.out.println( record.getRecordNumber()+": Event ID: " + record.getEventId()
                    + ", Event Type: " + record.getType()
                    + ", Event Source: " + record.getSource());
            */

            writerDown.println("Record Number: " + record.getRecordNumber()+": Event ID: " + record.getStatusCode()
                    + ", Event Type: " + record.getType()
                    + ", Event Source: " + record.getSource());
        }


        //Scanner scan=new Scanner(System.in);
        System.out.println("Please enter your times what you want per minutes: ");

        //int stoperTime=scan.nextInt();
        int stoperTime=Integer.valueOf(timesInput.getText());
        stoperTime=60/stoperTime;
        System.out.println("Please enter how many minutes what you want to run: ");
        //int loop=scan.nextInt();
        int loop=Integer.valueOf(minutesInput.getText());
        SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss");
        String startTime = date.format(new Date());
        writer.println(startTime);

        Timer timer=new Timer();
        int finalStoperTime = stoperTime;
        TimerTask task=new TimerTask() {
            int seconds=0;


            @Override
            public void run() {
                OperatingSystemMXBean osBean= ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);


                String cpuUsage=new DecimalFormat("##.#").format(osBean.getCpuLoad()*100);
                String memorySize=new DecimalFormat("##.#").format((osBean.getTotalMemorySize()-osBean.getFreeMemorySize())*0.000001);

                cpuUsage = cpuUsage.replace(",", ".");


                System.out.println("Saniye: "+seconds+" CPU Usage: "+ cpuUsage+" Memory Usage: "+ memorySize);

                seconds+= finalStoperTime;
                writer.println(cpuUsage);

                if(seconds==(loop*60)){
                    String finishTime = date.format(new Date());
                    Platform.runLater(() -> {
                        invisibleText.setText("Agent succesfully terminated. Check the txt file.");
                        finishedText.setText("Finished time: "+ finishTime);
                    });


                    writer.println(finishTime);
                    writer.close();
                    timer.cancel();
                }
            }

        };
        timer.schedule(task,0,(1000)*(stoperTime));
        invisibleText.setText("Agent is running!");
        startedText.setText("Started time: "+startTime);


    }
}