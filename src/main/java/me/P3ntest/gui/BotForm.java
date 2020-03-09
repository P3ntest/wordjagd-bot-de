package me.P3ntest.gui;

import me.P3ntest.Main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.nio.ByteOrder;

public class BotForm extends JFrame {

    JTextArea inputField;
    JButton goButton;
    public JTextField output;

    public static BotForm _instance;

    public BotForm() {
        _instance = this;
        setTitle("Wordjagd Bot - JVV");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        goButton = new JButton("Go!");
        inputField = new JTextArea();
        output = new JTextField();
        output.setColumns(20);

        Container container = getContentPane();

        container.add(goButton, BorderLayout.LINE_END);
        container.add(inputField, BorderLayout.CENTER);
        container.add(output, BorderLayout.SOUTH);

        goButton.addActionListener(actionEvent -> {
            System.out.println(inputField.getText().toLowerCase());
            Main.streamOutput(inputField.getText().toLowerCase(), output);
        });
    }

}
