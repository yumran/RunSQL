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
	  
	   JMenu fileMenu = new JMenu("�ļ�");
	   JMenu editMenu = new JMenu("�༭");
	   JMenu formatMenu = new JMenu("��ʽ");
	   JMenu checkMenu = new JMenu("�鿴");
	   JMenu helpMenu = new JMenu("����");
	  
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
	  
	   //�������ô�������Ļ��С�ı�
	   sizeWindowOnScreen(this,0.6,0.6);
	   this.setVisible(true);
	}

	private void sizeWindowOnScreen(RunSQLGui runSQLGui, double widthRate, double heightRatee) {
		// TODO �Զ����ɵķ������
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		runSQLGui.setSize(new Dimension((int)(screenSize.width * widthRate),(int)(screenSize.height*heightRatee)));
	}
	
	public static void main(String[] args)
	{
		RunSQLGui runSQLGui = new RunSQLGui();
	}
	
}
