package com.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class RunSQLGui extends JFrame{
	
	public RunSQLGui(){
		super();
	   JMenuBar menuBar = new JMenuBar();
	  
	   JMenu fileMenu = new JMenu("文件");
	   JMenu editMenu = new JMenu("编辑");
	   JMenu formatMenu = new JMenu("格式");
	   JMenu checkMenu = new JMenu("查看");
	   JMenu helpMenu = new JMenu("帮助");
	  
	   menuBar.add(fileMenu);
	   menuBar.add(editMenu);
	   menuBar.add(formatMenu);
	   menuBar.add(checkMenu);
	   menuBar.add(helpMenu);
	   
	   JScrollPane scrollPane = new JScrollPane();
	   scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	   scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	  
	   JTextArea textArea = new JTextArea();
	   scrollPane.setViewportView(textArea);
	  
	   this.getContentPane().add(scrollPane);
	  
	   this.setJMenuBar(menuBar);
	   this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	  
	   //用来设置窗口随屏幕大小改变
	   sizeWindowOnScreen(this,0.6,0.6);
	   this.setVisible(true);
	}

	private void sizeWindowOnScreen(RunSQLGui runSQLGui, double widthRate, double heightRatee) {
		// TODO 自动生成的方法存根
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		runSQLGui.setSize(new Dimension((int)(screenSize.width * widthRate),(int)(screenSize.height*heightRatee)));
	}
	
	public static void main(String[] args)
	{
		RunSQLGui runSQLGui = new RunSQLGui();
	}
	
}
