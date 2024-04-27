/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tp.text.editer;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 *
 * @author dev23
 */
public class TPTextEditer{
    private JFrame frame;
    private JMenuBar menuBar;
    private JTextPane textPane;
    
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
        FileMenuActionListener FileMenuActionListener = new FileMenuActionListener();
        newMenuItem.addActionListener(FileMenuActionListener);
        openMenuItem.addActionListener(FileMenuActionListener);
        saveMenuItem.addActionListener(FileMenuActionListener);
        saveAsMenuItem.addActionListener(FileMenuActionListener);
        exitMenuItem.addActionListener(FileMenuActionListener);
        
        
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
        EditMenuActionListener editMenuActionListener = new EditMenuActionListener();
        copyMenuItem.addActionListener(editMenuActionListener);
        cutMenuItem.addActionListener(editMenuActionListener);
        pasteMenuItem.addActionListener(editMenuActionListener);
        findMenuItem.addActionListener(editMenuActionListener);
        replaceMenuItem.addActionListener(editMenuActionListener);
        
        
        JMenu styleMenu = new JMenu("Style");
        JMenuItem normalMenuItem = new JMenuItem("Normal");
        JMenuItem boldMenuItem = new JCheckBoxMenuItem("Bold");
        JMenuItem italicMenuItem = new JCheckBoxMenuItem("Italic");
        JMenuItem underlineMenuItem = new JCheckBoxMenuItem("Underline");
        styleMenu.add(normalMenuItem);
        styleMenu.add(boldMenuItem);
        styleMenu.add(italicMenuItem);
        styleMenu.add(underlineMenuItem);
        StyleMenuActionListener styleMenuActionListener = new StyleMenuActionListener(styleMenu);
        boldMenuItem.addActionListener(styleMenuActionListener);
        italicMenuItem.addActionListener(styleMenuActionListener);
        underlineMenuItem.addActionListener(styleMenuActionListener);
        normalMenuItem.addActionListener(styleMenuActionListener);
        
        
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(styleMenu);
        
        
        textPane = new JTextPane();
        //textArea.setLineWrap(true);
        //textArea.setWrapStyleWord(true);
        textPane.setVisible(false);
        JScrollPane textAreaScrollPane = new JScrollPane(textPane);
        frame.add(textAreaScrollPane);
        
        
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new TPTextEditer();
    }

    private class FileMenuActionListener implements ActionListener {
        // this should stay here u dumbass not inside the method, that is why when open a file then save afterwards u find out that the file is null 
        // because once u click on another menuItem the the action performed methoed is relaunched and so it resets the file into null 
        // stupid dumb scope mistake 
        File file = null;
        
        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem menuItem = (JMenuItem) e.getSource();
            JFileChooser fileChooser = new JFileChooser();
            
            if( menuItem.getText().equals("New") ){
                textPane.setVisible(true);
                textPane.setText("");
                file = null;
            }
            
            else if (menuItem.getText().equals("Open")){
                textPane.setVisible(true);
                
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
                    textPane.setText(stringBuilder.toString());
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
                        bufferedWriter.write(textPane.getText());
                    
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
                            bufferedWriter.write(textPane.getText());
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
                            bufferedWriter.write(textPane.getText());
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
    
    private class StyleMenuActionListener implements ActionListener {
        //ColorUIResource originalBackgroundColor = new ColorUIResource(238, 238, 238);
        //ColorUIResource clickedBackgroundColor = new ColorUIResource(55, 170, 212);
        
        private JMenu styleMenu;
        
        public StyleMenuActionListener(JMenu styleMenu) {
            this.styleMenu = styleMenu;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem menuItem = (JMenuItem) e.getSource();
            String menuItemName = menuItem.getText();
            
            StyleContext sc = new StyleContext();
            
            int selectedTextStartPosition = textPane.getSelectionStart();
            int selectedTextEndPosition = textPane.getSelectionEnd();
            int selectedTextLength = selectedTextEndPosition - selectedTextStartPosition;
            StyledDocument doc = textPane.getStyledDocument();
            
            
            if (menuItemName.equals("Normal")){
                Component[] styleMenuItems = styleMenu.getMenuComponents();
                for (Component item : styleMenuItems) {
                    if (item instanceof JCheckBoxMenuItem) {
                        ((JCheckBoxMenuItem) item).setSelected(false);
                    }
                }
                
                    Style normalStyle = sc.getStyle(StyleContext.DEFAULT_STYLE);
                    textPane.setCharacterAttributes(normalStyle, true);
            }
            
            else if (menuItemName.equals("Bold")){
                if (menuItem.isSelected()) {
                    Style boldStyle = sc.addStyle("boldStyle", null);
                    // the attributeSet
                    StyleConstants.setBold(boldStyle, true);   
                    
                    // Apply the attribute set to the selected text range
                    doc.setCharacterAttributes(selectedTextStartPosition,selectedTextLength,boldStyle, false);
                }
            }
            
            else if (menuItemName.equals("Italic")){
                if (menuItem.isSelected()) {
                    Style italicStyle = sc.addStyle("ItalicStyle", null);
                    StyleConstants.setItalic(italicStyle, true);   
                    //textPane.setCharacterAttributes(italicStyle, true);
                    // Apply the attribute set to the selected text range
                    doc.setCharacterAttributes(selectedTextStartPosition,selectedTextLength,italicStyle, false);
                }
            }
            
            else if (menuItemName.equals("Underline")){
                if (menuItem.isSelected()) {
                    Style underlineStyle = sc.addStyle("underlineStyle", null);
                    StyleConstants.setUnderline(underlineStyle, true);   
                    //textPane.setCharacterAttributes(underlineStyle, true);
                    // Apply the attribute set to the selected text range
                    doc.setCharacterAttributes(selectedTextStartPosition,selectedTextLength,underlineStyle, false);
                }
            }
        }
    }


    private class EditMenuActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem menuItem = (JMenuItem) e.getSource();
            String menuItemName = menuItem.getText();
             
            
        }
    }
    
    
}

