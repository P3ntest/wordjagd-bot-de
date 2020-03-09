package me.P3ntest.gui;

import javax.swing.*;
import javax.swing.plaf.synth.SynthDesktopIconUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotAppForm {
    private JButton testButton;
    public JPanel rootPanel;

    public BotAppForm() {
        System.out.println(testButton);
        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Hello World");
            }
        });
    }
}
