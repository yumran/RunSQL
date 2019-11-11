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
		addJMenuBar();   //添加菜单栏
		addContentPanel(new MyTextArea());  //添加文本域
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//用来设置窗口随屏幕大小改变
		sizeWindowOnScreen(this, 0.8, 0.8);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/*
	 *    添加菜單
	 */
	public void addJMenuBar() {
		MyJMenuBar menuBar = new MyJMenuBar(this);   //创建菜单栏
		setJMenuBar(menuBar);
	}
	
	/*
	 *  添加文本域
	 */
	private void addContentPanel(Container contentPane) {
		this.getContentPane().add(contentPane,BorderLayout.CENTER);
	}
	
	/*
	 *  設置窗體大小
	 */
	public void sizeWindowOnScreen(JFrame jf, double widthRate, double heightRate) {
		// TODO 自动生成的方法存根
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		jf.setSize(new Dimension((int)(screenSize.width * widthRate),(int)(screenSize.height*heightRate)));
	}
}
