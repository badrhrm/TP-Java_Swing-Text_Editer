/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tp.text.editer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author dev23
 */
public class TPTextEditer{
    private JFrame frame;
    private JMenuBar menuBar;
    
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
        
        JMenu styleMenu = new JMenu("Style");
        JMenuItem boldMenuItem = new JMenuItem("Bold");
        JMenuItem italicMenuItem = new JMenuItem("Italic");
        JMenuItem underlineMenuItem = new JMenuItem("Underline");
        styleMenu.add(boldMenuItem);
        styleMenu.add(italicMenuItem);
        styleMenu.add(underlineMenuItem);
        
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(styleMenu);
        
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new TPTextEditer();
    }
}
