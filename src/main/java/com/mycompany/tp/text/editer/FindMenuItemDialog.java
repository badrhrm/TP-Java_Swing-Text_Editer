/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp.text.editer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 *
 * @author dev23
 */
public class FindMenuItemDialog {
    private JButton nextButton;
    private JButton previousButton;
    private JTextField textField;
    
    private JTextPane textPane;
    public FindMenuItemDialog(JTextPane textPane){
        this.textPane = textPane;
    }
    
    public void showDialog(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Find", true);
        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Find:");
        
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout());
        textField = new JTextField(20);
        textField.setFont(textField.getFont().deriveFont(Font.BOLD, 12f));
        textField.setPreferredSize(new Dimension(textField.getPreferredSize().width, 30));
        textPanel.add(textField);
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        nextButton = new BasicArrowButton(SwingConstants.SOUTH);
        previousButton = new BasicArrowButton(SwingConstants.NORTH);
        buttonsPanel.add(previousButton);
        buttonsPanel.add(nextButton);
        textPanel.add(buttonsPanel);
        
        MyActionListener myActionListener = new MyActionListener();
        nextButton.addActionListener(myActionListener);
        previousButton.addActionListener(myActionListener);
        
        
        dialog.getContentPane().add(label);
        dialog.getContentPane().add(textPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
    
    private class MyActionListener implements ActionListener {
        int listIndex = 0;
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            String inputText = textField.getText();
                String oraginalText = textPane.getText();
                int startPosition = 0;
                
                // Create a highlighter
                Highlighter highlighter = textPane.getHighlighter();
                // Create a highlight painter
                Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
                
            
            if (e.getSource() == nextButton) {
                List<Integer> allStartPositionsOfReoccurences = new ArrayList<>();
                
                for(int i=0; i<oraginalText.length();i++){
                    int isFound = oraginalText.indexOf(inputText,startPosition);
                    if ( isFound > -1 ){
                        int startPositionOfFoundInputText = oraginalText.indexOf(inputText,startPosition);
                        allStartPositionsOfReoccurences.add(startPositionOfFoundInputText);
                        startPosition += inputText.length();
                    }
                }
                
                if (allStartPositionsOfReoccurences.size() - 1 > listIndex) {
                    try {
                        int startIndex = allStartPositionsOfReoccurences.get(listIndex);
                        int endIndex = allStartPositionsOfReoccurences.get(listIndex) + inputText.length();
                        
                        // Apply the highlight to the word
                        highlighter.addHighlight(startIndex, endIndex, painter);
                        
                        listIndex++;
                    } catch (BadLocationException ex) {
                        Logger.getLogger(FindMenuItemDialog.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            } 
            
            Highlighter.Highlight[] highlights = highlighter.getHighlights();
            
            if (e.getSource() == previousButton) {
                if(listIndex > 0){
                     highlighter.removeHighlight(highlights[(highlights.length - 1)]);
                     listIndex--;
                }
            }
            
        }
    }
    
    
}
