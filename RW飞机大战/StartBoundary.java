package RW�ɻ���ս;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ɨ��.ClearMineModel;

public class StartBoundary implements ActionListener {
	private static JLabel label = new JLabel();
	JButton newGame, Record;
	NewGame New_Game;
	JFrame mainframe;
	static ClearMineModel model = new ClearMineModel();
	 private static ImageIcon background = new ImageIcon("C:\\Users\\Ausa\\Desktop\\ɨ��\\0"+1+".png");
//	private static ImageIcon background = new ImageIcon("F:\\ѧϰ�̳�\\weixinfeiji\\image\\��ʼ����.png");

	public StartBoundary() {
		mainframe = new JFrame("�ɻ���ս");
		newGame = new JButton("�µ���Ϸ");
		Record = new JButton("���а�");
		mainframe.setBounds(720, 100, 500, 820);
		Record.setBounds(200, 550, 100, 30);
		newGame.setBounds(200, 500, 100, 30);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// JPanel cp = (JPanel) mainframe.getContentPane();
		// �߿򲼾ֵĲ��ַ�ʽΪ�����ϱ���
		mainframe.setLayout(new BorderLayout()); // ���ô���Ĳ���Ϊ�߿򲼾�
		label.setIcon(background);
		mainframe.add(newGame);
		mainframe.add(Record);
		mainframe.add("Center", label); // ���LABEL�ؼ��������м�λ��
		newGame.addActionListener(this);
		Record.addActionListener(this);
		
		// ���������¼�
//		mainframe.addComponentListener(new ComponentAdapter() {
//			// ����componentResized����JFrame�����С�ı�ʱ���������¼�
//			public void componentResized(ComponentEvent e) {
//				mainframe.remove(label);
//				background = new ImageIcon(new ImageIcon("C:\\Users\\Ausa\\Desktop\\ɨ��\01.png").getImage()
//						.getScaledInstance(mainframe.getSize().width, mainframe.getSize().height, Image.SCALE_DEFAULT));
//				label.setIcon(background);
//				// label.setHorizontalAlignment(SwingConstants.CENTER);
//				mainframe.add(label);
//			}
//		});
		mainframe.setVisible(true);
	}

	// �����������жϵ�������ĸ���ť
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == newGame) {
			War game = new War();
			game.frame1(game);
		}
		if (e.getSource() == Record) {
			new Search();
			// mainframe.setVisible(false);
			mainframe.dispose();
		}
	}
}
