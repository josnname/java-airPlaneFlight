package RW�ɻ���ս;

	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;
	 
	//LoginFrame ��½����  ���� ʵ���û���½ ,����ɹ�����ת
	//LoginFrame �̳��ڴ�����, ��ʵ��ActionListener�ӿ�
	public class LoginBoundary extends JFrame implements ActionListener {
	    // ������Ҫ�����
		JFrame Login;
	    JButton jb1, jb2;//��ť 
	    JTextField jtf1;//�ı���
	    JPasswordField jpf1;//�����
	 
	    public LoginBoundary() {
	        // �������Ե�����
	        Login=new JFrame("��¼");
	        Login.setSize(500, 250);// ���ڴ�С
	        Login.setLocationRelativeTo(null);// ���ھ���
	        Login.setDefaultCloseOperation(EXIT_ON_CLOSE);// �رմ������˳������
	        Login.setLayout(new FlowLayout());// ���ò�����ʽ����
	 
	        JPanel jp = new JPanel(new GridLayout(4, 1));// �������Ϊ��񲼾�4��1��
	        // ��һ��
	        JPanel jp1 = new JPanel();
	        JLabel jl1 = new JLabel("�˺� ");
	        jtf1 = new JTextField(12);
	        jp1.add(jl1);
	        jp1.add(jtf1);
	        jp.add(jp1);
	        // �ڶ���
	        JPanel jp2 = new JPanel();
	        JLabel jl2 = new JLabel("���� ");
	        jpf1 = new JPasswordField(12);
	        jp2.add(jl2);
	        jp2.add(jpf1);
	        jp.add(jp2);
	        // ������
	        JPanel jp3 = new JPanel();
	        jb1 = new JButton("��½");
	        jb1.addActionListener(this);// ��Ӷ�����Ӧ��
	        jb2 = new JButton("����");
	        jb2.addActionListener(this);// ��Ӷ�����Ӧ��
	        jp3.add(jb1);
	        jp3.add(jb2);
	        jp.add(jp3);
	        // ������
	        JPanel jp4 = new JPanel();
//	        JLabel jl3 = new JLabel("��ʾ: �˺� admin ���� 123");
//	        jl3.setForeground(Color.DARK_GRAY);
//	        jp4.add(jl3);
	        jp.add(jp4);
	        Login.add(jp);
	        Login.setVisible(true);
	    }
	    // ������Ӧ
	    public void actionPerformed(ActionEvent e) {
	        String cmd = e.getActionCommand();// ���ݶ�������,�����зֱ���
	        if (cmd.equals("��½")) {
	            String id = jtf1.getText();// ȡ���û���
	            String key = new String(jpf1.getPassword());// ȡ������
	            if (id.equals("admin") && key.equals("123")) {// �ж��Ƿ��¼�ɹ�
	                // �����¼�ɹ�
	            	Login.setVisible(false);// ����������,
//	                new MainFrame(id).setVisible(true);// �´�����ʾ
	            	Login.dispose();//����������,�ͷ��ڴ���Դ
	            } else {
	                //�����¼ʧ��  ������ʾ
	                JOptionPane.showMessageDialog(this, "�û��������������.", "֪ͨ", JOptionPane.ERROR_MESSAGE);
	                jtf1.setText("");
	    	        jpf1.setText("");
	            }
	        } else if (cmd.equals("����")) {
	        	jtf1.setText("");
		        jpf1.setText("");
	        }
	 
	    }
//	 
//	    private void clearText() {//����ı���, ����������
//	        jtf1.setText("");
//	        jpf1.setText("");
//	    }
	     
	}

