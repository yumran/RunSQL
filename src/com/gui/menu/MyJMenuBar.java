package com.gui.menu;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.gui.DataSourseGUI;
import com.gui.Main;
import com.gui.pane.MyTextArea;
import com.gui.pane.SQLPanel;
import com.gui.pane.VoicePanel;

@SuppressWarnings("serial")
public class MyJMenuBar extends JMenuBar implements ActionListener{
	private Main mainGUI;

	public MyJMenuBar(Main mainGuI) {
		// TODO �Զ����ɵĹ��캯�����
		this.mainGUI = mainGuI;
		addMenu();
	}

	private void addMenu() {
		// TODO �Զ����ɵķ������
		String[] jmLabel = {"�ļ�","�༭","��ʽ","�鿴","SQL","����","ת��","����"};
		String[][] jmiLabel = {{"�½�","��","����","���Ϊ","�˳�"},{"����","����","����","ճ��","����","�滻"},{},{},{"ִ��","�Ա�"},{"����Դ"},{"����"},{}};
		for(int i=0;i<jmLabel.length;i++) {
			JMenu jm = new JMenu(jmLabel[i]);
			for(int j=0;j<jmiLabel[i].length;j++) {
				if("".equals(jmiLabel[i][j])) {
					
				}else {
					JMenuItem jmi = new JMenuItem(jmiLabel[i][j]);
					jmi.addActionListener(this);
					jm.addSeparator();   //�����  �˵��� �ĺ��߷ָ���
					jm.add(jmi);
				}
			}
			add(jm);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		// �л��������
		if(e.getActionCommand().equals("�½�")) {
			changePane(new MyTextArea());
		}else if(e.getActionCommand().equals("ִ��")) {
			changePane(new SQLPanel());
		}else if (e.getActionCommand().equals("�Ա�")) {
			
		}else if(e.getActionCommand().equals("����")) {
			changePane(new VoicePanel());
		}else if (e.getActionCommand().equals("����Դ")) {
			DataSourseGUI dataSourse = new DataSourseGUI(mainGUI);
		}
	}
	
	// �л��������
	private void changePane(Container panel) {
		// TODO �Զ����ɵķ������
		mainGUI.setContentPane(panel);
		mainGUI.revalidate();
	}
}
