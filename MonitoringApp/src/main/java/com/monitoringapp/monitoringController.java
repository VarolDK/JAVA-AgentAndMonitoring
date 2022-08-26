package com.monitoringapp;

import com.jfoenix.controls.JFXToggleButton;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;


public class monitoringController {

    public javafx.scene.control.Label catchText;

    public javafx.scene.control.Label avUsageLabel;

    public NumberAxis xAxis = new NumberAxis("Times",0,15,1);

    public NumberAxis yAxis = new NumberAxis("Usage",0,100,10);

    final CategoryAxis xEksen = new CategoryAxis();

    final NumberAxis yEksen = new NumberAxis();

    public LineChart<Number,Number> LineChart=new LineChart<Number,Number>(xAxis,yAxis);

    public BarChart<String,Number> barChart = new BarChart<>(xEksen,yEksen);
    public Label startDateLabel;
    public Label finishDateLabel;
    public ListView listView;
    public TextField takeEventDirectory;
    public TextField takeUsageDirectory;
    public Label catchTextTwo;
    public JFXToggleButton infoToggle;
    public JFXToggleButton warnToggle;
    public JFXToggleButton errToggle;
    public Label avgRamLabel;


    public void btn(javafx.event.ActionEvent event){

        NumberFormat formatter = new DecimalFormat("#0.00");

        boolean tryagain=true;


        //C:\Users\Varol\Desktop\cpuUsage.txt
        //C:\Users\Varol\Desktop\eventLog.txt

        double total=0;
        double ramTotal=0;
        int counter=0;


        catchText.setText("");

        XYChart.Series series= new XYChart.Series();

        XYChart.Series series1=new XYChart.Series();

        series.setName("CPU Usage");
        series1.setName("Memory Usage");


        do{
            try {

                String directory=takeUsageDirectory.getText();
                File file=new File(directory);


                BufferedReader br = new BufferedReader(new FileReader(file));
                BufferedReader br0 = new BufferedReader(new FileReader(file));
                BufferedReader br2 = new BufferedReader(new FileReader("C:\\Users\\Varol\\Desktop\\ramUsage.txt"));

                int lines = 0;
                while (br.readLine() != null) lines++;


                String st;
                String ram;


                while ((st = br0.readLine()) != null){
                    counter++;
                    ram = br2.readLine();
                    if (counter==1){
                        String startDate=st;
                        System.out.println(startDate);
                        startDateLabel.setText("Started Date: "+startDate);
                    } else if (counter==lines) {
                        String finishDate=st;
                        System.out.println(finishDate);
                        finishDateLabel.setText("Finished Date: "+finishDate);
                    }
                    if(counter != 1 && counter != lines){
                        total+=Double.parseDouble(st);
                        ramTotal+=Double.parseDouble(ram);
                        series.getData().add(new XYChart.Data(counter-1,Double.parseDouble(st)));
                        series1.getData().add(new XYChart.Data(""+counter,Double.parseDouble(ram)));

                    }

                    tryagain=true;

                }


            }catch (IOException fileEx) {
                System.out.println("Directory is unvalid. Please enter again valid directory: ");

                catchText.setText("Directory is unvalid. Please enter again valid directory.");


                tryagain=false;
                break;

            }
        }while(tryagain==false);
        System.out.println("Average CPU Usage: "+total/(counter-2));
        avUsageLabel.setText("Average CPU Usage: "+formatter.format(Math.abs(total/(counter-2))));
        avgRamLabel.setText("Average Memory Usage: "+formatter.format(Math.abs(ramTotal/(counter-2))));

        LineChart.getData().addAll(series);
        barChart.getData().addAll(series1);
    }

    public void btnInfo(javafx.event.ActionEvent event){

        if (infoToggle.isSelected()){

            String find="";


            find="Event Type: Informational";
            warnToggle.setSelected(false);
            errToggle.setSelected(false);
            listView.getItems().clear();


            boolean tryagain=true;


            catchTextTwo.setText("");

            do{
                try {

                    String directory=takeEventDirectory.getText();
                    File file=new File(directory);


                    BufferedReader br = new BufferedReader(new FileReader(file));
                    BufferedReader br0 = new BufferedReader(new FileReader(file));

                    int lines = 0;
                    while (br.readLine() != null) lines++;


                    String st;

                    while ((st = br0.readLine()) != null){
                        if(st.contains(find)){
                            listView.getItems().add(st);

                        }
                        tryagain=true;

                    }


                }catch (IOException fileEx) {
                    System.out.println("Directory is unvalid. Please enter again valid directory: ");

                    catchTextTwo.setText("Directory is unvalid. Please enter again valid directory.");


                    tryagain=false;
                    break;

                }
            }while(tryagain==false);


        }else listView.getItems().clear();



    }

    public void btnWarning(javafx.event.ActionEvent event){

        if(warnToggle.isSelected()){

            String find="";

            find="Event Type: Warning";
            infoToggle.setSelected(false);
            errToggle.setSelected(false);
            listView.getItems().clear();


            boolean tryagain=true;



            catchTextTwo.setText("");

            do{
                try {

                    String directory=takeEventDirectory.getText();
                    File file=new File(directory);


                    BufferedReader br = new BufferedReader(new FileReader(file));
                    BufferedReader br0 = new BufferedReader(new FileReader(file));

                    int lines = 0;
                    while (br.readLine() != null) lines++;


                    String st;

                    while ((st = br0.readLine()) != null){
                        if(st.contains(find)){
                            listView.getItems().add(st);

                        }
                        tryagain=true;

                    }


                }catch (IOException fileEx) {
                    System.out.println("Directory is unvalid. Please enter again valid directory: ");

                    catchTextTwo.setText("Directory is unvalid. Please enter again valid directory.");


                    tryagain=false;
                    break;

                }
            }while(tryagain==false);

        }else listView.getItems().clear();



    }

    public void btnError(javafx.event.ActionEvent event){

        if(errToggle.isSelected()){

            String find="";


            find="Event Type: Error";
            infoToggle.setSelected(false);
            warnToggle.setSelected(false);
            listView.getItems().clear();


            boolean tryagain=true;


            catchTextTwo.setText("");

            do{
                try {

                    String directory=takeEventDirectory.getText();
                    File file=new File(directory);


                    BufferedReader br = new BufferedReader(new FileReader(file));
                    BufferedReader br0 = new BufferedReader(new FileReader(file));

                    int lines = 0;
                    while (br.readLine() != null) lines++;


                    String st;

                    while ((st = br0.readLine()) != null){
                        if(st.contains(find)){
                            listView.getItems().add(st);

                        }
                        tryagain=true;

                    }


                }catch (IOException fileEx) {
                    System.out.println("Directory is unvalid. Please enter again valid directory: ");

                    catchTextTwo.setText("Directory is unvalid. Please enter again valid directory.");


                    tryagain=false;
                    break;

                }
            }while(tryagain==false);

        }else listView.getItems().clear();

    }

}

