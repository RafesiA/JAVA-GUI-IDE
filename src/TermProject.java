import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TermProject extends JFrame {
	public TermProject() {
		setTitle("Java IDE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(new FlowLayout());
		
		
		JButton bt1 = new JButton("Java File Upload");
		JButton bt2 = new JButton("Compile");
		JButton bt3 = new JButton("Run");
		JButton bt4 = new JButton("Reset");
		JButton bt5 = new JButton("Compile Error List");
		JButton bt6 = new JButton("Exit");

		bt1.addActionListener(new MyActionListener());
		bt2.addActionListener(new MyActionListener());
		bt3.addActionListener(new MyActionListener());
		bt4.addActionListener(new MyActionListener());
		bt5.addActionListener(new MyActionListener());
		bt6.addActionListener(new MyActionListener());
		
		contentPane.add(bt1);
		contentPane.add(bt2);
		contentPane.add(bt3);
		contentPane.add(bt4);
		contentPane.add(bt5);
		contentPane.add(bt6);
		
		setSize(350, 150);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		TermProject frame = new TermProject();
	}

}

class MyActionListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		
		
		
		
		if(b.getText().equals("Java File Upload")) {
			b.setText("�׼� ������ ����");
		}
		else if(b.getText().equals("Compile")) {
			b.setText("�׼� ������ ���� ����");
		}
		else if(b.getText().equals("Run")) {
			b.setText("Run ������");
		}
		else if(b.getText().equals("Reset")) {
			b.setText("�ʱ�ȭ �Ǿ���");
		}
		else if(b.getText().equals("Compile Error List")){
			b.setText("CEL�� TextArea�� ��µ�");
		}
		else if(b.getText().equals("Exit")) {
			System.exit(-1);
		}
	}
}
