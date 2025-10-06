package org.example.practice;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// ----- MODEL -----
class MVCModel {
    private String name;

    public MVCModel() {
        name = "Hello";
    }

    public String getName() {
        return name;
    }
}

// ----- VIEW -----
class MVCView extends JFrame {
    JButton button;
    JTextArea textArea;

    public MVCView() {
        setTitle("Simple MVC Example");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        button = new JButton("Click Me");
        textArea = new JTextArea(5, 20);

        add(button);
        add(textArea);

        setVisible(true);
    }

    public void appendText(String text) {
        textArea.append(text + "\n");
    }

    public void addListener(ActionListener listener) {
        button.addActionListener(listener);
    }
}

// ----- CONTROLLER -----
public class MVC implements ActionListener {
    private MVCModel model;
    private MVCView view;

    public MVC(MVCModel model, MVCView view) {
        this.model = model;
        this.view = view;

        // Link the view and controller
        view.addListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // When button is clicked, update the view using model data
        String msg = model.getName();
        view.appendText(msg);
    }

    // ----- MAIN -----
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MVCModel model = new MVCModel();
            MVCView view = new MVCView();
            new MVC(model, view);
        });
    }
}
