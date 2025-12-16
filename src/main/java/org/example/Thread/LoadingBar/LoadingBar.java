package org.example.Thread.LoadingBar;

import javax.swing.*;
import java.util.List;

public class LoadingBar extends JFrame {

    public JLabel completed;
    public JLabel loading;
    JButton button;
    JPanel panel;

    LoadingBar() {
        completed = new JLabel("");
        loading = new JLabel("Loading...");
        button = new JButton("Click me!");
        button.addActionListener(e->{
            new HelperClass().execute();
        });

        panel = new JPanel(); // default FlowLayout

        // add components to panel
        panel.add(loading);
        panel.add(completed);
        panel.add(button);

        // add panel to frame
        this.add(panel);

        this.setTitle("Loading Bar");
        this.setSize(300, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // center
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new LoadingBar();
    }


    class HelperClass extends SwingWorker<Void,String> {

        @Override
        protected Void doInBackground() throws Exception {
            button.setEnabled(false);
            Integer i =0;
            for(i = 0;i<100;i+=10){
                Thread.sleep(1000);
                publish(Integer.toString(i));
            }
            return null;
        }

        @Override
        protected void process(List<String> msgs){
            for (String s: msgs){
                loading.setText(s);
            }
        }

        @Override
        protected void done(){
            completed.setText("Completed");

            button.setEnabled(true);
        }

    }
}

