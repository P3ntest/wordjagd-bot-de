package me.P3ntest.gui;

import me.P3ntest.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BotForm extends JFrame implements KeyListener {

    JTextField inputField;
    JButton goButton;
    public JTextArea output;

    public static BotForm _instance;

    public BotForm() {
        _instance = this;
        setTitle("Wordjagd Bot - JVV");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());

        goButton = new JButton("Go!");
        inputField = new JTextField();

        inputField.addKeyListener(this);

        output = new JTextArea();
        output.setColumns(20);

        Font font = new Font("sans-serif", Font.PLAIN, 30);
        output.setFont(font);

        Container container = getContentPane();

        container.add(output, BorderLayout.CENTER);
        container.add(goButton, BorderLayout.EAST);
        container.add(inputField, BorderLayout.SOUTH);


        goButton.addActionListener(actionEvent -> actuated());
    }

    private void actuated() {
        String input = inputField.getText().toLowerCase();
        boolean doIt = true;
        if (input.length() < 16) {
            doIt = false;
            JOptionPane.showMessageDialog(null, "Bitte gebe mindestens 16 Zeichen ein.");
        }

        if (doIt)
            Main.streamOutput(input, output);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            actuated();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
