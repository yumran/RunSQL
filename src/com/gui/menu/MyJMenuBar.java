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
		// TODO 自动生成的构造函数存根
		this.mainGUI = mainGuI;
		addMenu();
	}

	private void addMenu() {
		// TODO 自动生成的方法存根
		String[] jmLabel = {"文件","编辑","格式","查看","SQL","设置","转换","帮助"};
		String[][] jmiLabel = {{"新建","打开","保存","另存为","退出"},{"撤销","剪切","复制","粘贴","查找","替换"},{},{},{"执行","对比"},{"数据源"},{"语音"},{}};
		for(int i=0;i<jmLabel.length;i++) {
			JMenu jm = new JMenu(jmLabel[i]);
			for(int j=0;j<jmiLabel[i].length;j++) {
				if("".equals(jmiLabel[i][j])) {
					
				}else {
					JMenuItem jmi = new JMenuItem(jmiLabel[i][j]);
					jmi.addActionListener(this);
					jm.addSeparator();   //这个是  菜单中 的横线分隔符
					jm.add(jmi);
				}
			}
			add(jm);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		// 切换内容面板
		if(e.getActionCommand().equals("新建")) {
			changePane(new MyTextArea());
		}else if(e.getActionCommand().equals("执行")) {
			changePane(new SQLPanel());
		}else if (e.getActionCommand().equals("对比")) {
			
		}else if(e.getActionCommand().equals("语音")) {
			changePane(new VoicePanel());
		}else if (e.getActionCommand().equals("数据源")) {
			DataSourseGUI dataSourse = new DataSourseGUI(mainGUI);
		}
	}
	
	// 切换内容面板
	private void changePane(Container panel) {
		// TODO 自动生成的方法存根
		mainGUI.setContentPane(panel);
		mainGUI.revalidate();
	}
}
