/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tp.text.editer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
        JMenuItem exitMenuItem = new JMenuItem("Exit Program");
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);
        MyMenuItemActionListener myMenuItemActionListener = new MyMenuItemActionListener();
        newMenuItem.addActionListener(myMenuItemActionListener);
        openMenuItem.addActionListener(myMenuItemActionListener);
        saveMenuItem.addActionListener(myMenuItemActionListener);
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
        
        
        
        
        
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new TPTextEditer();
    }
    
    

    private class MyMenuItemActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    }
    
}
