package com.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import com.gui.menu.MyJMenuBar;
import com.gui.pane.MyTextArea;

@SuppressWarnings("serial")
public class Main extends JFrame{
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main m = new Main();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Main() {
		super();
		addJMenuBar();   //��Ӳ˵���
		addContentPanel(new MyTextArea());  //����ı���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//�������ô�������Ļ��С�ı�
		sizeWindowOnScreen(this, 0.8, 0.8);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/*
	 *    ��Ӳˆ�
	 */
	public void addJMenuBar() {
		MyJMenuBar menuBar = new MyJMenuBar(this);   //�����˵���
		setJMenuBar(menuBar);
	}
	
	/*
	 *  ����ı���
	 */
	private void addContentPanel(Container contentPane) {
		this.getContentPane().add(contentPane,BorderLayout.CENTER);
	}
	
	/*
	 *  �O�ô��w��С
	 */
	public void sizeWindowOnScreen(JFrame jf, double widthRate, double heightRate) {
		// TODO �Զ����ɵķ������
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		jf.setSize(new Dimension((int)(screenSize.width * widthRate),(int)(screenSize.height*heightRate)));
	}
}
