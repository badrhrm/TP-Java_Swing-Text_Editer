/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp.text.editer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author dev23
 */
public class FileNameInputDialog {
    private JTextField textField;
    private String fileName;

    public String showDialog(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Enter File Name", true);
        textField = new JTextField(20);
        JButton saveButton = new JButton("Save");
        
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileName = textField.getText().trim();
                dialog.dispose();  
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Enter File Name:"), BorderLayout.NORTH);
        panel.add(textField, BorderLayout.CENTER);
        panel.add(saveButton, BorderLayout.SOUTH);
        dialog.getContentPane().add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
        
        return fileName;
    }
    
}
