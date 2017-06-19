package com.ufg.i4soft.angelix_plugin.view.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChooseRepair extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton angelixRadioButton;
    private JRadioButton genProgRadioButton;

    private static String choiceRepair;

    public static String getChoiceRepair() {
        return choiceRepair;
    }

    public ChooseRepair() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        // centerWindows();
        OneChoise();

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {

        if (angelixRadioButton.isSelected()) {
            choiceRepair = "angelix";
        } else if (genProgRadioButton.isSelected()) {
            choiceRepair = "genprog";
        }

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void OneChoise() {

        ButtonGroup group = new ButtonGroup();
        group.add(angelixRadioButton);
        group.add(genProgRadioButton);
    }

    private void centerWindows() {

        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int width = (screenSize.width - getWidth()) / 2;
        final int height = (screenSize.height - getHeight()) / 2;
        setLocation(width, height);
    }

    public static void main(String[] args) {
        ChooseRepair dialog = new ChooseRepair();
        dialog.pack();
        dialog.setTitle("Escolha da Ferramenta de Reparo");
        dialog.setSize(500, 300);
        dialog.centerWindows();
        dialog.setVisible(true);
    }
}
