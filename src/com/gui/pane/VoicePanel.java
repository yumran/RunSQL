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
	
	private JTextArea textArea;   //�ı���
	private JButton toVoiceBtn;   //����ת������ť
	private JButton toTextBtn;    //����ת���ְ�ť
	private JLabel volumeJLa;     //��������
	private JSlider volumeJsl;    //��������
	private JLabel speedJLa;      //�ٶȱ���
	private JSlider speedJsl;     //�ٶ�
	
	public VoicePanel() {
		init();
	}

	private void init() {
		// TODO �Զ����ɵķ������
		SpringLayout springLayout = new SpringLayout();
		this.setLayout(springLayout);
		
		JPanel taPanel = new JPanel(new BorderLayout());
		textArea = new JTextArea();
		textArea.setRows(10);
		MyTextArea.addMargin(textArea);
		taPanel.add(textArea,BorderLayout.CENTER);
		
		JPanel btnPanel = new JPanel(new FlowLayout());
		toVoiceBtn = new JButton("�ϳ�����");
		toTextBtn = new JButton("¼������");
		toVoiceBtn.addActionListener(this);
		toTextBtn.addActionListener(this);
		btnPanel.add(toTextBtn);
		btnPanel.add(toVoiceBtn);
		
		JPanel volumePanel = new JPanel(new BorderLayout());
		volumeJLa = new JLabel("����");
		volumeJLa.setFont(new Font("Dialog", 1, 20));
		volumeJsl = new JSlider(0, 100, 20);
		//����������Ӽ�����
		volumeJsl.addChangeListener(this);
		 // �������̶ȼ��
		volumeJsl.setMajorTickSpacing(10);
        // ���ôο̶ȼ��
		volumeJsl.setMinorTickSpacing(5);
        // ���� �̶� �� ��ǩ
		volumeJsl.setPaintTicks(true);
		volumeJsl.setPaintLabels(true);
		volumePanel.add(volumeJLa,BorderLayout.WEST);
		volumePanel.add(volumeJsl,BorderLayout.CENTER);
		
		JPanel speedPanel = new JPanel(new BorderLayout());
		speedJLa = new JLabel("�ٶ�");
		speedJLa.setFont(new Font("Dialog", 1, 20));
		speedJsl = new JSlider(0, 20, 10);
		//�ٶȻ�����Ӽ�����
		speedJsl.addChangeListener(this);
		 // �������̶ȼ��
		speedJsl.setMajorTickSpacing(5);
        // ���ôο̶ȼ��
		speedJsl.setMinorTickSpacing(1);
        // ���� �̶� �� ��ǩ
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
	
	//  ¼�ơ��ϳ� ��ť�����ļ�������
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		if(e.getSource()==toVoiceBtn) {
			transform(textArea.getText().trim());
		}else if (e.getSource()==toTextBtn) {
			System.out.println("����ɣ�");
		}
	}
	
	//  �������ٶ� �����ı�ʱ������������
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO �Զ����ɵķ������
		if(e.getSource()==volumeJsl) {
			setting(volumeJsl.getValue(),speedJsl.getValue());
		}else if (e.getSource()==toTextBtn) {
			System.out.println("����ɣ�");
		}
	}
	
	//���� �������ٶ�
	private void setting(int volume, int speed) {
		// TODO �Զ����ɵķ������
		if(sap == null||sap.m_pDispatch==0) {
			sap = new ActiveXComponent("Sapi.SpVoice");
		}
		// ���� 0-100
        sap.setProperty("Volume", new Variant(volume));
        // �����ʶ��ٶ� -10 �� +10
        sap.setProperty("Rate", new Variant(speed));
	}
	
	//ת������
	private void transform(String text) {
		// TODO �Զ����ɵķ������
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
