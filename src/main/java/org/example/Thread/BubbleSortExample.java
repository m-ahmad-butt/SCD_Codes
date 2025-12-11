package org.example.Thread;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class BubbleSortExample extends JFrame {

    private JButton startBtn;
    private JButton clickBtn;
    private JTextArea area;
    private JLabel info;
    private JProgressBar bar;

    public BubbleSortExample() {
        setTitle("SwingWorker publish/process Demo");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        startBtn = new JButton("Start Sorting");
        clickBtn = new JButton("Click Me");
        area = new JTextArea();
        info = new JLabel("UI Active");
        area.setEditable(false);
        bar = new JProgressBar(0, 100);

        JPanel top = new JPanel();
        top.add(startBtn);
        top.add(clickBtn);

        setLayout(new BorderLayout());
        add(top, BorderLayout.NORTH);
        add(new JScrollPane(area), BorderLayout.CENTER);

        JPanel south = new JPanel(new BorderLayout());
        south.add(info, BorderLayout.WEST);
        south.add(bar, BorderLayout.SOUTH);
        add(south, BorderLayout.SOUTH);

        startBtn.addActionListener(e -> {
            startBtn.setEnabled(false);
            area.append("Started bubble sort...\n");
            new SortWorker().execute();
        });

        clickBtn.addActionListener(e -> info.setText("Clicked at " + System.nanoTime()));
    }

    class SortWorker extends SwingWorker<Void,String> {

        @Override
        protected Void doInBackground() {

            int n = 10000000;
            int[] a = new int[n];
            Random r = new Random();
            for(int i=0;i<n;i++) a[i] = r.nextInt();

            long st = System.currentTimeMillis();

            for(int i=0;i<n-1;i++) {
                boolean sw = false;

                for(int j=0;j<n-i-1;j++) {
                    if(a[j] > a[j+1]) {
                        int t = a[j];
                        a[j] = a[j+1];
                        a[j+1] = t;
                        sw = true;
                    }
                }

                if(!sw) break;

                int p = (int)((i * 100.0) / n);
                setProgress(p);

                if(i % 5000 == 0) {
                    publish("i=" + i + "  progress=" + p + "%");
                }
            }

            long en = System.currentTimeMillis();
            publish("Done in " + (en - st) + " ms");

            return null;
        }

        @Override
        protected void process(List<String> msgs) {
            for(String s : msgs) {
                area.append(s + "\n");
                bar.setValue(getProgress());
                area.setCaretPosition(area.getDocument().getLength());
            }
        }

        @Override
        protected void done() {
            area.append("Sorting completed.\n");
            startBtn.setEnabled(true);
        }
    }

    public static void main(String[] x) {
        SwingUtilities.invokeLater(() -> new BubbleSortExample().setVisible(true));
    }
}
