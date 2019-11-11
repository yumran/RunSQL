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
	private JLabel dataTypeLabel;    //���ݿ�����   MYSQL SQL SERVER 
	private JLabel dataHostLabel;     //����
	private JLabel dataPortLabel;    //�˿�
	private JLabel dataBaseLabel;    //���ݿ�
	private JLabel userNameLabel;    //�û���
	private JLabel passWordLabel;    //����
	
	private JComboBox<Object> dataTypeField;  //���ݿ�����    ����ѡ��
	private JTextField dataHostField;         //����       �ı���
	private JTextField dataPortField;         //�˿�       �ı���
	private JComboBox<Object> dataBaseField;  //���ݿ�       ����ѡ��
	private JTextField userNameField;        //�û���            �ı���
	private JTextField passWordField;        //����                 �ı���
	
	private JButton connectTestBut;
	private JButton submitBut;
	
	public DataSourseGUI(Main mainGUI) {
		init();
		setSize(400,330);
		setLocationRelativeTo(null);
		setVisible(true);
		mainGUI.setEnabled(false);//��������������Ϊ�ɲ�����
		addWindowListener(new WindowAdapter() {              //��Ӵ��ڹرյļ�����
			public void windowClosing(WindowEvent e) {  
				 mainGUI.setEnabled(true);    //��������������Ϊ�ɲ�����
	         }  
		});
	}

	private void init() {
		// TODO �Զ����ɵķ������
		JPanel jPanel = new JPanel();
		JPanel jPanel_1 = new JPanel(new FlowLayout());
		JPanel jPanel_2 = new JPanel(new FlowLayout());
		JPanel jPanel_3 = new JPanel(new FlowLayout());
		JPanel jPanel_4 = new JPanel(new FlowLayout());
		JPanel jPanel_5 = new JPanel(new FlowLayout());
		JPanel jPanel_6 = new JPanel(new FlowLayout());
		JPanel jPanel_7 = new JPanel(new FlowLayout());
		((FlowLayout) jPanel_7.getLayout()).setHgap(122);   //��������ť֮��ļ��
		
		dataTypeLabel = new JLabel("��    ��:",JLabel.RIGHT);
		dataHostLabel = new JLabel("������:",JLabel.RIGHT);
		dataPortLabel = new JLabel("��    ��:",JLabel.RIGHT);
		dataBaseLabel = new JLabel("���ݿ�:",JLabel.RIGHT);
		userNameLabel = new JLabel("�û���:",JLabel.RIGHT);
		passWordLabel = new JLabel("��    ��:",JLabel.RIGHT);
		
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
		
		connectTestBut = new JButton("���Ӳ���");
		connectTestBut.addActionListener(this);   //Ϊ���Ӳ�����Ӽ���
		submitBut = new JButton("ȷ��");
		submitBut.addActionListener(this);    //Ϊȷ����Ӽ���
		
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
	 * ��ť�ļ����¼�     1�� ���ݿ����Ӳ���     2��ȷ��
 	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		
		String dataBase = (String) dataTypeField.getSelectedItem();
		String dataHost = dataHostField.getText().trim();
		String dataPort = dataPortField.getText().trim();
		String userName = userNameField.getText().trim();
		String passWord = passWordField.getText().trim();
		
		String[] args = {dataBase,dataHost,dataPort,userName,passWord};
		
		
		if(StrIsEmpty(args)) {
			
		}else {
			// ���   ���Ӳ���   ��ť
			if(e.getSource() == connectTestBut) {
				if(JdbcUtil.getConnection(dataBase, dataHost, dataPort, userName, passWord)==null) {
					JOptionPane.showMessageDialog(null, "���ݿ�����ʧ�ܣ�","����",JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "���ݿ����ӳɹ���");
					String sql = "SELECT SCHEMA_NAME FROM information_schema.SCHEMATA";
					System.out.println(JdbcUtil.executeQuery(sql));
					JdbcUtil.close();
				}
			}
			
			// ���    ȷ��   ��ť
			if(e.getSource() == submitBut) {
				
			}
		}
	}

	private boolean StrIsEmpty(String[] args) {
		// TODO �Զ����ɵķ������
		Boolean flag = false;
		String[] message = {"��ѡ�����ݿ⣡","������������ַ��","������˿ںţ�","�������û�����","���������룡"};
		for(int i=0;i<args.length;i++) {
			if(i==4) {
				continue;
			}else {
				if(args[i].isEmpty()||"".equals(args[i])) {
					flag = true;
					JOptionPane.showMessageDialog(null, message[i],"����",JOptionPane.ERROR_MESSAGE);break;
				}
			}
		}
		return flag;
	}
}
