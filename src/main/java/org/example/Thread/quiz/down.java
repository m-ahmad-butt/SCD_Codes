package org.example.Thread.quiz;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class down extends JFrame {
    JTextArea textArea;
    JButton button;

    public down() {
        textArea = new JTextArea("");
        button = new JButton("Start");
        Panel panel = new Panel(new GridLayout(2, 1));
        panel.add(textArea);
        panel.add(button);
        setLayout(new FlowLayout());
        add(panel);
        setSize(400, 400);
        setVisible(true);
        button.addActionListener(
                e->{
                    new Thread(new R()).start();
                }
        );
    }

    public class R implements Runnable {
        @Override
        public void run() {
            List<String>l = download();
           SwingUtilities.invokeLater(
                   ()->{
                       if(l==null || l.isEmpty()){
                           textArea.setText("Download Failed");
                       }
                       else{
                           textArea.setText("");
                           for(String s: l){
                               textArea.append(s+"\n");
                           }
                       }
                   }
           );
        }
    }

    //just assume its a function
    private List<String> download(){
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        Double t = Math.random();
        if(t < 0.25){
            return List.of();
        }
        else{
            return List.of("1.mp3","2.mp3");
        }
    }
    public static void main(String[] args) {
        new down();
    }
}
