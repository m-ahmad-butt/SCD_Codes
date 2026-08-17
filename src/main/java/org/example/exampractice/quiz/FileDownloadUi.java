package org.example.exampractice.quiz;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

// download(url) runs on a worker thread (Runnable).
// success -> file path, failure -> "". UI is updated on the EDT.
public class FileDownloadUi extends JFrame {
    private final JTextArea urlArea;
    private final JTextArea statusArea;
    private final JButton downloadBtn;

    public FileDownloadUi() {
        setTitle("File Downloader");
        setSize(560, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        urlArea = new JTextArea("https://httpbin.org/bytes/64\nhttps://httpbin.org/image/png");
        statusArea = new JTextArea();
        statusArea.setEditable(false);
        downloadBtn = new JButton("Download");

        JPanel top = new JPanel(new BorderLayout(4, 4));
        top.add(new JLabel("URLs (one per line)"), BorderLayout.NORTH);
        top.add(new JScrollPane(urlArea), BorderLayout.CENTER);
        top.add(downloadBtn, BorderLayout.SOUTH);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(statusArea), BorderLayout.CENTER);

        downloadBtn.addActionListener(e -> startDownloads());
        setLocationRelativeTo(null);
    }

    private void startDownloads() {
        String[] urls = urlArea.getText().split("\\R");
        statusArea.setText("");
        downloadBtn.setEnabled(false);

        for (String raw : urls) {
            String url = raw.trim();
            if (url.isEmpty()) {
                continue;
            }
            new Thread(new DownloadTask(url)).start();
        }

        downloadBtn.setEnabled(true);
    }

    String download(String url) {
        try {
            URL u = new URL(url);
            String name = url.substring(url.lastIndexOf('/') + 1);
            if (name.isBlank() || name.contains("?")) {
                name = "file-" + System.currentTimeMillis();
            }

            File dir = new File("data/downloads");
            dir.mkdirs();
            File out = new File(dir, name);

            try (InputStream in = u.openStream();
                 FileOutputStream fos = new FileOutputStream(out)) {
                byte[] buf = new byte[4096];
                int n;
                while ((n = in.read(buf)) != -1) {
                    fos.write(buf, 0, n);
                }
            }
            return out.getAbsolutePath();
        } catch (Exception e) {
            return "";
        }
    }

    class DownloadTask implements Runnable {
        private final String url;

        DownloadTask(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            String path = download(url);
            SwingUtilities.invokeLater(() -> {
                if (path.isEmpty()) {
                    statusArea.append("FAIL  " + url + "\n");
                } else {
                    statusArea.append("SUCCESS  " + path + "\n");
                }
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FileDownloadUi().setVisible(true));
    }
}
