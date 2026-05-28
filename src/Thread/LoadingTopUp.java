package thread;

import javax.swing.*;

public class LoadingTopUp
extends Thread {

    JFrame frame;

    public LoadingTopUp(
            JFrame frame
    ){

        this.frame = frame;

    }

    @Override
    public void run(){

        try{

            JDialog loading =

            new JDialog(
                    frame,
                    "Loading",
                    true
            );

            JLabel text =

            new JLabel(
                    "Memproses Pembayaran..."
            );

            text.setHorizontalAlignment(
                    JLabel.CENTER
            );

            loading.add(text);

            loading.setSize(
                    250,
                    100
            );

            loading.setLocationRelativeTo(
                    frame
            );

            new Thread(){

                @Override
                public void run(){

                    try{

                        sleep(2000);

                    }

                    catch(Exception e){}

                    loading.dispose();

                }

            }.start();

            loading.setVisible(true);

        }

        catch(Exception e){

            System.out.println(
                    e.getMessage()
            );

        }

    }

}