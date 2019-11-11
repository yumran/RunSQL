package com.gui.pane;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class ScrollPaneClass extends JScrollPane{   
	private JPanel taPanel;    //文本域面板
	private JTextArea textArea;  //文本域
	
	public ScrollPaneClass() {
		init();
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}

	private void init() {
		// TODO 自动生成的方法存根
		taPanel = new JPanel(new BorderLayout());
		textArea = new JTextArea();
		//为TextArea条件边距
		addMargin(textArea);
		taPanel.add(textArea,BorderLayout.CENTER);
		this.setViewportView(taPanel);
	}

	private void addMargin(JTextArea textArea) {
		// TODO 自动生成的方法存根
		Border border = BorderFactory.createEmptyBorder();     //创建一个不占用空间的空边框。（顶线、底线、左边框线和右边框线的宽度都为零。） 
		textArea.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(5,5,5,5))); 
	}
}
