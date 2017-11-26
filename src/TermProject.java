import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.List;
import java.util.Arrays;



public class TermProject extends JFrame {
	String FileName;
	File E_file = new File("C:\\Temp\\Error_File.txt");
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	static int CR = 1;							// ��������, 1 = Disable Run function, 0 = Enable Run function
	static int CO = 1;                          // ��������, 1 = Disable Compile function, 0 = Enable Compile function
	static int CP = 1;                          // ��������, 1 = Disable Compile Error list function, 0 = Enable Compile Error list function
	static int errorList = 1;					// ��������, 1 = ���� ���� && not found 0 = ������ ������ ���� ��
	
	JButton btn1 = new JButton("Java File Upload");
	JButton btn2 = new JButton("Compile");
	JButton btn3 = new JButton("Run Program");
	JButton btn4 = new JButton("Compile Error List");
	JButton btn5 = new JButton("Clear");
	JButton btn6 = new JButton("Exit");
	JButton btn7 = new JButton("Save");
	JButton btn8 = new JButton("Delete");
	JTextField jt = new JTextField("File Directory", 20);
	JTextArea st = new JTextArea("Status \n");//Status ���
	JTextArea ja = new JTextArea("Editor" + "\n");//Editor
	JTextArea er = new JTextArea("Error Message, Result" + "\n");//Error ���� ���
	JTextField sf = new JTextField("Save File Title");
	JLabel txt = new JLabel("���� ��θ� �Է��ϰ� ��ư�� Ŭ���ϼ���.");
	
	
	class SPanel extends JPanel{
		SPanel(){
			setVisible(true);
			setSize(1000,100);
			setLayout(new BorderLayout());
			st.setSize(1000,100);
			add(st,BorderLayout.CENTER);
			add(new JScrollPane(st));
		}
	}
	class EPanel extends JPanel{
		EPanel(){
			setVisible(true);
			setSize(1000,300);
			setLayout(new BorderLayout());
			ja.setSize(900, 450);
			add(ja,BorderLayout.CENTER);
			add(new JScrollPane(ja));
		}
	}
	
	class RPanel extends JPanel{
		RPanel(){
			setVisible(true);
			setSize(1000,150);
			setLayout(new BorderLayout());
			er.setSize(900, 150);
			add(er,BorderLayout.CENTER);
			JScrollPane scr = new JScrollPane();
			
			add(new JScrollPane(er));
		}
	}

	public TermProject() {
		SPanel s = new SPanel();
		EPanel e = new EPanel();
		RPanel r = new RPanel();
		setResizable(false);
		setTitle("Java IDE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.gray);
		MyActionListener al = new MyActionListener();
		setLayout(null);
		
		txt.setSize(1000, 50);
		txt.setLocation(380, 0);
		contentPane.add(txt);
		
		jt.setSize(800, 100);
		jt.setLocation(0, 50);
		contentPane.add(jt);
		
		btn1.setToolTipText("�ڹ� ������ ���ε��մϴ�.");
		btn1.setSize(200, 50);
		btn1.setLocation(800, 50);
		contentPane.add(btn1);
		
		
		sf.setSize(800,100);
		sf.setLocation(0, 150);
		contentPane.add(sf);
		
		s.setLocation(0, 250);
		contentPane.add(s);
		
		btn2.setToolTipText("�������� �����մϴ�.");
		btn2.setSize(200, 50);
		btn2.setLocation(800,100);
		contentPane.add(btn2);
		
		
		e.setLocation(0, 350);
		contentPane.add(e);
		
		btn3.setToolTipText("���α׷��� �����մϴ�.");
		btn3.setSize(250, 100);
		btn3.setLocation(0,650);
		contentPane.add(btn3);
		
		btn4.setToolTipText("���� �޼����� ����մϴ�.");
		btn4.setSize(250, 100);
		btn4.setLocation(250,650);
		contentPane.add(btn4);
		
		btn5.setToolTipText("���α׷��� �ʱ�ȭ�մϴ�.");
		btn5.setSize(250, 100);
		btn5.setLocation(500,650);
		contentPane.add(btn5);
		
		btn6.setToolTipText("���α׷��� �����մϴ�.");
		btn6.setSize(250, 100);
		btn6.setLocation(750,650);
		contentPane.add(btn6);
		
		btn7.setToolTipText("������ �����մϴ�.");
		btn7.setSize(200,50);
		btn7.setLocation(800, 150);
		contentPane.add(btn7);
		
		btn8.setToolTipText("������ �����մϴ�.");
		btn8.setSize(200, 50);
		btn8.setLocation(800, 200);
		contentPane.add(btn8);
		
		
		r.setLocation(0,750);
		contentPane.add(r);
		
	
		ja.setEditable(true);
		er.setEditable(false);
		st.setEditable(false);
		
		btn1.addActionListener(al);
		
		
		btn2.addActionListener(al);
		
		btn3.addActionListener(al);
		
		btn4.addActionListener(al);
		
		btn5.addActionListener(al);
		
		btn6.addActionListener(al);
		
		btn7.addActionListener(al);
		
		btn8.addActionListener(al);
		
	
		
		ToolTipManager m = ToolTipManager.sharedInstance();
		m.setInitialDelay(0);
		m.setDismissDelay(2000);
		
		setSize(1000,930);
		setVisible(true);
	}
	class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			if(b.getText().equals("Java File Upload")) {
					try {
						FileReader fr = null;
						FileName = jt.getText();
						st.append(FileName + "\n");
						BufferedReader br = new BufferedReader(new FileReader(FileName));
						ja.read(br, FileName);
						br.close();
						E_file.delete();
						CO = 0;
						errorList = 1;
						CR = 1;
						} catch(IOException er){
							String errorMessage = er.getMessage();
							System.out.println(er);
							CO = 1;
							errorList = 1;
						}
					}
				//void UploadJ();
			else if(b.getText().equals("Compile")) {
				if(FileName != null && CO == 0) {
				st.append("������ ����" + "\n");
				btn3.setEnabled(true);
				String s = null;
				try {
					Process oProcess = new ProcessBuilder("javac", FileName).start();
					BufferedReader stdError = new BufferedReader(new InputStreamReader
				(oProcess.getErrorStream()));
					while ((s = stdError.readLine()) != null) {
						BufferedWriter fw = new BufferedWriter(new FileWriter(E_file, true));
						fw.write(s);
						fw.write(LINE_SEPARATOR);
						fw.flush();
						fw.close();
					}
					CR = 0;
					CP = 0;
					errorList = 0;
					
				} catch(IOException e1) {
					System.out.println(e1);
				}
				if(E_file.exists() == true) {
					st.append("������ ����" +"\n");
					st.append(FileName + " ������ ���������� ������ ���� �ʾҽ��ϴ�." + "\n");
					CR = 1;
					CP = 0;
					errorList = 1;
					}
				}
				 else if(CO == 1) {
					 st.append("������ ������ ã�� �� �����ϴ�.\n");
					 CR = 0;
				 }
					
					
					
				//void Compile();
			}
			
			else if(b.getText().equals("Save")) {
				String et = ja.getText();
				String FilePath = "C:\\temp\\" +  sf.getText() + ".java";
				File f = new File(FilePath);
				
				
				
				if(sf.getText().isEmpty() == true)
				
					try {
					PrintWriter pw = new PrintWriter(jt.getText());
					pw.print("");
					pw.close();
					
					FileWriter rw = new FileWriter(jt.getText(),true);
					rw.write(et);
					rw.flush();
					rw.close();
					st.append("���� ����� ����.\n");
					
					}catch(IOException w) {
						w.printStackTrace();
					}
				
				else if(!f.exists())
					try {
						f.createNewFile();
						FileWriter fw = new FileWriter(f,true);
						fw.write(et);
						fw.flush();
						fw.close();
						
					} catch (IOException q) {
						q.printStackTrace();
					}
				
				else
					st.append("������ �̹� �����մϴ�.\n");			
			}
			
			else if(b.getText().equals("Run Program")) {
				if(CR == 0 && CO == 0) {
					File file = new File(FileName);
					String fname = file.getName();
					String path = file.getParent();
					int pos = fname.lastIndexOf(".");
					if(pos > 0) {
						fname = fname.substring(0, pos);
					}
					List<String> cmds = Arrays.asList("cmd", "/c", "cd", path, "&&", "java", fname);
					try {
						String s;
						Process rProcess = new ProcessBuilder(cmds).start();
						BufferedReader stdOut = new BufferedReader(new InputStreamReader(rProcess.getInputStream()));
						BufferedReader stdError = new BufferedReader(new InputStreamReader(rProcess.getErrorStream()));
						st.append(FileName + " �� ������\n");
						errorList = 0;
						while ((s = stdOut.readLine()) != null) { 
							er.append(s);
							er.append("\n");
						}
						while ((s = stdError.readLine()) != null) {
							er.append(s);
							er.append("\n");
						}
					} catch(IOException e2) {
						st.append("ġ���� ����");
					}
					b.setVisible(true);
				}
			 else if(CR == 1 || CO == 1) {
				st.append("������ ���ε� ���� �ʾҰų�, �������� �ȵ�\n");
				btn3.setEnabled(false);
			}
				
			}
			    //void Run();
			else if(b.getText().equals("Compile Error List")) {
				if(CP == 0 && errorList == 1) {
					try {
						FileReader reader = null;
						int c;
						BufferedReader br = new BufferedReader(new FileReader(E_file));
						reader = new FileReader("C:\\Temp\\Error_File.txt");
						er.read(br, E_file);
						er.append("\n");
						reader.close();
						br.close();
					} catch(IOException w) {
						System.out.println("Error!\n");
					}
				} else if(CP == 1 && CO == 1) {
					st.append("������ ���ε���� �ʾҽ��ϴ�.\n");
				} else if(errorList == 0) {
					st.append("�ش� ���Ͽ� ������ ������ �����ϴ�\n");
				} else if(CP == 1) {
					st.append("�������� ���� �ʾҽ��ϴ�.\n");
				}
			}
			    //void Compile_E();
			else if(b.getText().equals("Clear")) {
				try {
					ja.setText("Editor\n");
					jt.setText("File Directory");
					st.setText("Status\n");
					er.setText("Error Message, Result\n");	
					sf.setText("Save File Title");
					FileName = null;
					btn3.setEnabled(true);
					CR = 1;
					CO = 1;
					CP = 1;
					Files.deleteIfExists(E_file.toPath());
					st.append("�ʱ�ȭ �Ǿ����ϴ�\n");
					ja.setText("");
				} catch(IOException x) {
					System.err.println(x);
				}
			}
			    //void Clear();
			else if(b.getText().equals("Exit")) {
				E_file.delete();
				System.exit(0);
			}
			else if(b.getText().equals("Delete")) {
				File D_file = new File(jt.getText());
				
				if(D_file.exists() == true) {
				D_file.delete();
				st.append("������ �����Ǿ����ϴ�.\n");
				}
				else
					st.append("������ ������ �������� �ʽ��ϴ�.\n");
				
			
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		new TermProject();
	}

}
