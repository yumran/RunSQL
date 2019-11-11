package com.gui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class test {
	public static void main(String[] args) {
	    JLabel label = new JLabel("Username :", JLabel.RIGHT);
	    label.setVerticalAlignment(JLabel.TOP);
	    
	    JOptionPane.showMessageDialog(null, label);
	    
	  }
}
