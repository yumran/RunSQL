package com.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dao.JdbcUtil;

@SuppressWarnings("serial")
public class DataSourseGUI extends JFrame implements ActionListener{	
	private JLabel dataTypeLabel;    //数据库类型   MYSQL SQL SERVER 
	private JLabel dataHostLabel;     //主机
	private JLabel dataPortLabel;    //端口
	private JLabel dataBaseLabel;    //数据库
	private JLabel userNameLabel;    //用户名
	private JLabel passWordLabel;    //密码
	
	private JComboBox<Object> dataTypeField;  //数据库类型    下拉选框
	private JTextField dataHostField;         //主机       文本框
	private JTextField dataPortField;         //端口       文本框
	private JComboBox<Object> dataBaseField;  //数据库       下拉选框
	private JTextField userNameField;        //用户名            文本框
	private JTextField passWordField;        //密码                 文本框
	
	private JButton connectTestBut;
	private JButton submitBut;
	
	public DataSourseGUI(Main mainGUI) {
		init();
		setSize(400,330);
		setLocationRelativeTo(null);
		setVisible(true);
		mainGUI.setEnabled(false);//将主界面再设置为可操作的
		addWindowListener(new WindowAdapter() {              //添加窗口关闭的监听类
			public void windowClosing(WindowEvent e) {  
				 mainGUI.setEnabled(true);    //将主界面再设置为可操作的
	         }  
		});
	}

	private void init() {
		// TODO 自动生成的方法存根
		JPanel jPanel = new JPanel();
		JPanel jPanel_1 = new JPanel(new FlowLayout());
		JPanel jPanel_2 = new JPanel(new FlowLayout());
		JPanel jPanel_3 = new JPanel(new FlowLayout());
		JPanel jPanel_4 = new JPanel(new FlowLayout());
		JPanel jPanel_5 = new JPanel(new FlowLayout());
		JPanel jPanel_6 = new JPanel(new FlowLayout());
		JPanel jPanel_7 = new JPanel(new FlowLayout());
		((FlowLayout) jPanel_7.getLayout()).setHgap(122);   //设置两按钮之间的间距
		
		dataTypeLabel = new JLabel("类    型:",JLabel.RIGHT);
		dataHostLabel = new JLabel("主机名:",JLabel.RIGHT);
		dataPortLabel = new JLabel("端    口:",JLabel.RIGHT);
		dataBaseLabel = new JLabel("数据库:",JLabel.RIGHT);
		userNameLabel = new JLabel("用户名:",JLabel.RIGHT);
		passWordLabel = new JLabel("密    码:",JLabel.RIGHT);
		
		dataTypeField = new JComboBox<>();
		dataTypeField.setPreferredSize(new Dimension(220, 20));
		dataTypeField.addItem("MYSQL");
		dataTypeField.addItem("SQL SERVER");
		
		dataHostField = new JTextField("localhost",20);
		dataPortField = new JTextField("3306",20);
		
		dataBaseField = new JComboBox<>();
		dataBaseField.setPreferredSize(new Dimension(220, 20));
		
		userNameField = new JTextField("root",20);
		passWordField = new JTextField(20);
		
		connectTestBut = new JButton("连接测试");
		connectTestBut.addActionListener(this);   //为连接测试添加监听
		submitBut = new JButton("确定");
		submitBut.addActionListener(this);    //为确定添加监听
		
		jPanel_1.add(dataTypeLabel);
		jPanel_1.add(dataTypeField);
		
		jPanel_2.add(dataHostLabel);
		jPanel_2.add(dataHostField);
		
		jPanel_3.add(dataPortLabel);
		jPanel_3.add(dataPortField);
		
		jPanel_4.add(dataBaseLabel);
		jPanel_4.add(dataBaseField);
		
		jPanel_5.add(userNameLabel);
		jPanel_5.add(userNameField);
		
		jPanel_6.add(passWordLabel);
		jPanel_6.add(passWordField);
		
		jPanel_7.add(connectTestBut);
		jPanel_7.add(submitBut);
		
		jPanel.add(jPanel_1);
		jPanel.add(jPanel_2);
		jPanel.add(jPanel_3);
		jPanel.add(jPanel_4);
		jPanel.add(jPanel_5);
		jPanel.add(jPanel_6);
		jPanel.add(jPanel_7);
		
		this.getContentPane().add(jPanel);
	}
	
	/*
	 * 按钮的监听事件     1、 数据库连接测试     2、确定
 	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		
		String dataBase = (String) dataTypeField.getSelectedItem();
		String dataHost = dataHostField.getText().trim();
		String dataPort = dataPortField.getText().trim();
		String userName = userNameField.getText().trim();
		String passWord = passWordField.getText().trim();
		
		String[] args = {dataBase,dataHost,dataPort,userName,passWord};
		
		
		if(StrIsEmpty(args)) {
			
		}else {
			// 点击   连接测试   按钮
			if(e.getSource() == connectTestBut) {
				if(JdbcUtil.getConnection(dataBase, dataHost, dataPort, userName, passWord)==null) {
					JOptionPane.showMessageDialog(null, "数据库连接失败！","错误",JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "数据库连接成功！");
					String sql = "SELECT SCHEMA_NAME FROM information_schema.SCHEMATA";
					System.out.println(JdbcUtil.executeQuery(sql));
					JdbcUtil.close();
				}
			}
			
			// 点击    确定   按钮
			if(e.getSource() == submitBut) {
				
			}
		}
	}

	private boolean StrIsEmpty(String[] args) {
		// TODO 自动生成的方法存根
		Boolean flag = false;
		String[] message = {"请选择数据库！","请输入主机地址！","请输入端口号！","请输入用户名！","请输入密码！"};
		for(int i=0;i<args.length;i++) {
			if(i==4) {
				continue;
			}else {
				if(args[i].isEmpty()||"".equals(args[i])) {
					flag = true;
					JOptionPane.showMessageDialog(null, message[i],"错误",JOptionPane.ERROR_MESSAGE);break;
				}
			}
		}
		return flag;
	}
}
