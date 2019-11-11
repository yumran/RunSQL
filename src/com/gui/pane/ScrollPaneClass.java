package com.gui.pane;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class ScrollPaneClass extends JScrollPane{   
	private JPanel taPanel;    //�ı������
	private JTextArea textArea;  //�ı���
	
	public ScrollPaneClass() {
		init();
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}

	private void init() {
		// TODO �Զ����ɵķ������
		taPanel = new JPanel(new BorderLayout());
		textArea = new JTextArea();
		//ΪTextArea�����߾�
		addMargin(textArea);
		taPanel.add(textArea,BorderLayout.CENTER);
		this.setViewportView(taPanel);
	}

	private void addMargin(JTextArea textArea) {
		// TODO �Զ����ɵķ������
		Border border = BorderFactory.createEmptyBorder();     //����һ����ռ�ÿռ�Ŀձ߿򡣣����ߡ����ߡ���߿��ߺ��ұ߿��ߵĿ�ȶ�Ϊ�㡣�� 
		textArea.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(5,5,5,5))); 
	}
}
