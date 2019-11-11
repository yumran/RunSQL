package com.gui.pane;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

@SuppressWarnings("serial")
public class SQLPanel extends JPanel{
	private JTextArea textArea;   //文本域
	private JButton runBtn;   //执行按钮
	private JTable table;     //结果
	private JLabel timeLabel;  //执行时间
	
	public SQLPanel() {
		init();
	}

	private void init() {
		// TODO 自动生成的方法存根
		SpringLayout springLayout = new SpringLayout();
		this.setLayout(springLayout);
		
		JPanel taPanel = new JPanel(new BorderLayout());
		textArea = new JTextArea();
		textArea.setRows(10);
		MyTextArea.addMargin(textArea);
		taPanel.add(textArea,BorderLayout.CENTER);
		
		JPanel btnPanel = new JPanel(new BorderLayout());
		runBtn = new JButton("Run");
		btnPanel.add(runBtn,BorderLayout.WEST);
		
		JPanel tablePanel = new JPanel(new BorderLayout());
		table = new JTable();
		tablePanel.add(table,BorderLayout.CENTER);
		
		JPanel timePanel = new JPanel(new BorderLayout());
		timeLabel = new JLabel("Time:",JLabel.LEFT);
		timePanel.add(timeLabel,BorderLayout.WEST);
	
		this.add(taPanel);
		this.add(btnPanel);
		this.add(tablePanel);
		this.add(timePanel);
		
		springLayout.putConstraint(SpringLayout.NORTH, taPanel, 5, SpringLayout.NORTH, this);          //
		springLayout.putConstraint(SpringLayout.WEST, taPanel, 5, SpringLayout.WEST, this);            //
		springLayout.putConstraint(SpringLayout.EAST, taPanel, -5, SpringLayout.EAST, this);           //
		
		springLayout.putConstraint(SpringLayout.NORTH, btnPanel, 5, SpringLayout.SOUTH, taPanel);      //
		springLayout.putConstraint(SpringLayout.WEST, btnPanel, 5, SpringLayout.WEST, this);           //
		
		springLayout.putConstraint(SpringLayout.NORTH, tablePanel, 5, SpringLayout.SOUTH, btnPanel);   //
		springLayout.putConstraint(SpringLayout.WEST, tablePanel, 5, SpringLayout.WEST, this);         //
		springLayout.putConstraint(SpringLayout.EAST, taPanel, -5, SpringLayout.EAST, this);            //
		
		springLayout.putConstraint(SpringLayout.WEST, timePanel, 5, SpringLayout.WEST, this);          //
		springLayout.putConstraint(SpringLayout.SOUTH, timePanel, -10, SpringLayout.SOUTH, this);      //
	}
}
