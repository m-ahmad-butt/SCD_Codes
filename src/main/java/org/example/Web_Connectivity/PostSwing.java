package org.example.Web_Connectivity;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostSwing {

    public static void main(String[] args) {
        JFrame f = new JFrame("POST Demo");

        JTextField field = new JTextField();
        JTextArea area = new JTextArea();

        JButton btn = new JButton("Send");
        btn.setPreferredSize(new Dimension(80, 25));

        JPanel top = new JPanel(new BorderLayout());
        top.add(field, BorderLayout.CENTER);
        top.add(btn, BorderLayout.EAST);

        f.setLayout(new BorderLayout());
        f.add(top, BorderLayout.NORTH);
        f.add(new JScrollPane(area), BorderLayout.CENTER);

        btn.addActionListener(e -> {
            new Thread(() -> {
                try {
                    URL url = new URL("https://httpbin.org/post");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    con.setDoOutput(true);
                    con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                    String s = field.getText();
                    String data = "msg=" + java.net.URLEncoder.encode(s, "UTF-8");

                    OutputStream os = con.getOutputStream();
                    os.write(data.getBytes());
                    os.close();

                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(con.getInputStream())
                    );

                    String line;
                    String res = "";
                    while ((line = br.readLine()) != null) {
                        res += line + "\n";
                    }
                    br.close();

                    String out = res;
                    SwingUtilities.invokeLater(() -> area.setText(out));

                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> area.setText(ex.toString()));
                }
            }).start();
        });

        f.setSize(500, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
