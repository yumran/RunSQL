package com.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Toolkit;

import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTable;

import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

public class mainGui extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainGui frame = new mainGui();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainGui() {
		super();
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u6587\u4EF6");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("\u7F16\u8F91");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("\u683C\u5F0F");
		menuBar.add(mnNewMenu_2);
		
		JMenu mnNewMenu_3 = new JMenu("\u67E5\u770B");
		menuBar.add(mnNewMenu_3);
		
		JMenu mnNewMenu_4 = new JMenu("\u5E2E\u52A9");
		menuBar.add(mnNewMenu_4);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		JPanel jPanel = new JPanel(new BorderLayout());
		JTextArea textArea = new JTextArea();
		textArea.setRows(5);
		jPanel.add(textArea,BorderLayout.NORTH);
		
		JPanel jPanel2 = new JPanel();
		jPanel.add(jPanel2,BorderLayout.CENTER);
		jPanel2.setLayout(new BorderLayout(0, 0));
		
		JButton button1 = new JButton("Run");
		jPanel2.add(button1,BorderLayout.NORTH);
		
		JTable table1 = new JTable();
		jPanel2.add(table1,BorderLayout.CENTER);
		scrollPane.setViewportView(jPanel);
		
		JLabel label1 = new JLabel("Time:");
		jPanel2.add(label1,BorderLayout.SOUTH);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		JPanel jPanel_1 = new JPanel(new BorderLayout());
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setRows(5);
		jPanel_1.add(textArea_1,BorderLayout.NORTH);
		
		JPanel jPanel3 = new JPanel(new BorderLayout());
		jPanel_1.add(jPanel3,BorderLayout.CENTER);
		JButton button2 = new JButton("Run");
		jPanel3.add(button2,BorderLayout.NORTH);
		
		JTable table2 = new JTable();
		jPanel3.add(table2,BorderLayout.CENTER);
		
		JLabel label2 = new JLabel("Time:");
		jPanel3.add(label2,BorderLayout.SOUTH);
		
		scrollPane_1.setViewportView(jPanel_1);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scrollPane,scrollPane_1);
		splitPane.setOneTouchExpandable(true);
		splitPane.setContinuousLayout(true);
		//splitPane.setDividerLocation(0.5);
		//scrollPane.setRowHeaderView(splitPane);
		//this.getContentPane().add(scrollPane);
		this.getContentPane().add(splitPane);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//用来设置窗口随屏幕大小改变
		sizeWindowOnScreen(this, 0.6, 0.6);
		setLocationRelativeTo(null);
		setVisible(true);
		splitPane.setDividerLocation(0.5);
	}

	private void sizeWindowOnScreen(mainGui mainGui, double widthRate, double heightRate) {
		// TODO 自动生成的方法存根
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		mainGui.setSize(new Dimension((int)(screenSize.width * widthRate),(int)(screenSize.height*heightRate)));
	}
}
