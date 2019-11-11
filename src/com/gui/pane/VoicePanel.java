package com.gui.pane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

@SuppressWarnings("serial")
public class VoicePanel extends JPanel implements ActionListener,ChangeListener{
	private ActiveXComponent sap = null;
	
	private JTextArea textArea;   //文本域
	private JButton toVoiceBtn;   //文字转语音按钮
	private JButton toTextBtn;    //语音转文字按钮
	private JLabel volumeJLa;     //音量标题
	private JSlider volumeJsl;    //音量滑条
	private JLabel speedJLa;      //速度标题
	private JSlider speedJsl;     //速度
	
	public VoicePanel() {
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
		
		JPanel btnPanel = new JPanel(new FlowLayout());
		toVoiceBtn = new JButton("合成语音");
		toTextBtn = new JButton("录制语音");
		toVoiceBtn.addActionListener(this);
		toTextBtn.addActionListener(this);
		btnPanel.add(toTextBtn);
		btnPanel.add(toVoiceBtn);
		
		JPanel volumePanel = new JPanel(new BorderLayout());
		volumeJLa = new JLabel("音量");
		volumeJLa.setFont(new Font("Dialog", 1, 20));
		volumeJsl = new JSlider(0, 100, 20);
		//音量滑条添加监听器
		volumeJsl.addChangeListener(this);
		 // 设置主刻度间隔
		volumeJsl.setMajorTickSpacing(10);
        // 设置次刻度间隔
		volumeJsl.setMinorTickSpacing(5);
        // 绘制 刻度 和 标签
		volumeJsl.setPaintTicks(true);
		volumeJsl.setPaintLabels(true);
		volumePanel.add(volumeJLa,BorderLayout.WEST);
		volumePanel.add(volumeJsl,BorderLayout.CENTER);
		
		JPanel speedPanel = new JPanel(new BorderLayout());
		speedJLa = new JLabel("速度");
		speedJLa.setFont(new Font("Dialog", 1, 20));
		speedJsl = new JSlider(0, 20, 10);
		//速度滑条添加监听器
		speedJsl.addChangeListener(this);
		 // 设置主刻度间隔
		speedJsl.setMajorTickSpacing(5);
        // 设置次刻度间隔
		speedJsl.setMinorTickSpacing(1);
        // 绘制 刻度 和 标签
		speedJsl.setPaintTicks(true);
		speedJsl.setPaintLabels(true);
		speedPanel.add(speedJLa,BorderLayout.WEST);
		speedPanel.add(speedJsl,BorderLayout.CENTER);
		
		this.add(taPanel);
		this.add(volumePanel);
		this.add(speedPanel);
		this.add(btnPanel);

		springLayout.putConstraint(SpringLayout.NORTH, taPanel, 5, SpringLayout.NORTH, this);          //
		springLayout.putConstraint(SpringLayout.WEST, taPanel, 5, SpringLayout.WEST, this);            //
		springLayout.putConstraint(SpringLayout.EAST, taPanel, -300, SpringLayout.EAST, this); 
		
		springLayout.putConstraint(SpringLayout.NORTH, volumePanel, 15, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, volumePanel, 10, SpringLayout.EAST, taPanel);
		springLayout.putConstraint(SpringLayout.EAST, volumePanel, -10, SpringLayout.EAST, this);
		
		springLayout.putConstraint(SpringLayout.NORTH, speedPanel, 20, SpringLayout.SOUTH, volumePanel);
		springLayout.putConstraint(SpringLayout.WEST, speedPanel, 10, SpringLayout.EAST, taPanel);
		springLayout.putConstraint(SpringLayout.EAST, speedPanel, -10, SpringLayout.EAST, this);
		
		springLayout.putConstraint(SpringLayout.NORTH, btnPanel, 5, SpringLayout.SOUTH, taPanel);      //
		springLayout.putConstraint(SpringLayout.WEST, btnPanel, 0, SpringLayout.WEST, this);  
	}
	
	//  录制、合成 按钮触发的监听机制
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource()==toVoiceBtn) {
			transform(textArea.getText().trim());
		}else if (e.getSource()==toTextBtn) {
			System.out.println("待完成！");
		}
	}
	
	//  音量、速度 滑条改变时触发监听机制
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource()==volumeJsl) {
			setting(volumeJsl.getValue(),speedJsl.getValue());
		}else if (e.getSource()==toTextBtn) {
			System.out.println("待完成！");
		}
	}
	
	//设置 音量、速度
	private void setting(int volume, int speed) {
		// TODO 自动生成的方法存根
		if(sap == null||sap.m_pDispatch==0) {
			sap = new ActiveXComponent("Sapi.SpVoice");
		}
		// 音量 0-100
        sap.setProperty("Volume", new Variant(volume));
        // 语音朗读速度 -10 到 +10
        sap.setProperty("Rate", new Variant(speed));
	}
	
	//转化函数
	private void transform(String text) {
		// TODO 自动生成的方法存根
		if(!text.isEmpty()&&!text.equals("")) {
		    if(sap == null||sap.m_pDispatch==0) {
		    	sap = new ActiveXComponent("Sapi.SpVoice");
		    }
			Dispatch sapo = sap.getObject();
		    try {
		    	setting(volumeJsl.getValue(),speedJsl.getValue());
		        Dispatch.call(sapo, "Speak", new Variant(text));
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        sapo.safeRelease();
		        sap.safeRelease();
		    }
		}
	}
}
