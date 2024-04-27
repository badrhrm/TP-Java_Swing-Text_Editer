/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tp.text.editer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author dev23
 */
public class TPTextEditer{
    private JFrame frame;
    private JMenuBar menuBar;
    private JTextArea textArea;
    
    public TPTextEditer(){
        frame = new JFrame();
        frame.setTitle("TP Text Editer");
        frame.setSize(600,400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        
        
        
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem saveAsMenuItem = new JMenuItem("Save As");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsMenuItem);
        fileMenu.add(exitMenuItem);
        MyMenuItemActionListener myMenuItemActionListener = new MyMenuItemActionListener();
        newMenuItem.addActionListener(myMenuItemActionListener);
        openMenuItem.addActionListener(myMenuItemActionListener);
        saveMenuItem.addActionListener(myMenuItemActionListener);
        saveAsMenuItem.addActionListener(myMenuItemActionListener);
        exitMenuItem.addActionListener(myMenuItemActionListener);
        
        
        JMenu editMenu = new JMenu("Edit");
        JMenuItem copyMenuItem = new JMenuItem("Copy");
        JMenuItem cutMenuItem = new JMenuItem("Cut");
        JMenuItem pasteMenuItem = new JMenuItem("Paste");
        JMenuItem findMenuItem = new JMenuItem("Find");
        JMenuItem replaceMenuItem = new JMenuItem("Replace");
        editMenu.add(copyMenuItem);
        editMenu.add(cutMenuItem);
        editMenu.add(pasteMenuItem);
        editMenu.add(findMenuItem); 
        editMenu.add(replaceMenuItem);  
        copyMenuItem.addActionListener(myMenuItemActionListener);
        cutMenuItem.addActionListener(myMenuItemActionListener);
        pasteMenuItem.addActionListener(myMenuItemActionListener);
        findMenuItem.addActionListener(myMenuItemActionListener);
        replaceMenuItem.addActionListener(myMenuItemActionListener);
        
        
        JMenu styleMenu = new JMenu("Style");
        JMenuItem boldMenuItem = new JMenuItem("Bold");
        JMenuItem italicMenuItem = new JMenuItem("Italic");
        JMenuItem underlineMenuItem = new JMenuItem("Underline");
        styleMenu.add(boldMenuItem);
        styleMenu.add(italicMenuItem);
        styleMenu.add(underlineMenuItem);
        boldMenuItem.addActionListener(myMenuItemActionListener);
        italicMenuItem.addActionListener(myMenuItemActionListener);
        underlineMenuItem.addActionListener(myMenuItemActionListener);
        
        
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(styleMenu);
        
        
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setVisible(false);
        JScrollPane textAreaScrollPane = new JScrollPane(textArea);
        frame.add(textAreaScrollPane);
        
        
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new TPTextEditer();
    }
    
    

    private class MyMenuItemActionListener implements ActionListener {
        // this should stay here u dumbass not inside the method, that is why when open a file then save afterwards u find out that the file is null 
        // because once u click on another menuItem the the action performed methoed is relaunched and so it resets the file into null 
        // stupid dumb scope mistake 
        File file = null;
        
        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem menuItem = (JMenuItem) e.getSource();
            JFileChooser fileChooser = new JFileChooser();
            
            if( menuItem.getText().equals("New") ){
                textArea.setVisible(true);
                textArea.setText("");
                file = null;
            }
            
            else if (menuItem.getText().equals("Open")){
                textArea.setVisible(true);
                
                try {
                    // Show file choosing interface
                    int result = fileChooser.showOpenDialog(frame);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        file = fileChooser.getSelectedFile();
                    }

                    // importing the file's text to TextArea Interface
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    // Read the file line by line
                    String line;
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((line = bufferedReader.readLine()) != null) {
                        // this goes through each line and appends it 
                        stringBuilder.append(line).append("\n"); // poteniel issue : .append("\n") means we always have an extra newline at the end of any opened file
                    }
                    textArea.setText(stringBuilder.toString());
                    bufferedReader.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            
            else if (menuItem.getText().equals("Save")){
                
                try {
                    if(file != null){
                        // saving the new modification on the opened file 
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                        bufferedWriter.write(textArea.getText());
                    
                        bufferedWriter.close();
                    }
                    else {
                        // create a new file and store textArea in it 
                        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        int result = fileChooser.showOpenDialog(frame);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            File directory = fileChooser.getSelectedFile();
                            FileNameInputDialog  fileNameInputDialog = new FileNameInputDialog();
                            String name = fileNameInputDialog.showDialog(frame);
                            file = new File(directory, name);
                            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                            bufferedWriter.write(textArea.getText());
                            bufferedWriter.close();
                    
                        }
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            
            else if (menuItem.getText().equals("Save As")){
                try {
                        // create a new file and store textArea in it 
                        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        int result = fileChooser.showOpenDialog(frame);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            File directory = fileChooser.getSelectedFile();
                            FileNameInputDialog  fileNameInputDialog = new FileNameInputDialog();
                            String name = fileNameInputDialog.showDialog(frame);
                            file = new File(directory, name);
                            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                            bufferedWriter.write(textArea.getText());
                            bufferedWriter.close();
                        }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                
            }
            
            else if (menuItem.getText().equals("Exit")){
                frame.dispose();
            }
        }
    }
    
    
    
}
