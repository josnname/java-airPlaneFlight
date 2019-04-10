package RW飞机大战;

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

import 扫雷.ClearMineModel;

public class StartBoundary implements ActionListener {
	private static JLabel label = new JLabel();
	JButton newGame, Record;
	NewGame New_Game;
	JFrame mainframe;
	static ClearMineModel model = new ClearMineModel();
	 private static ImageIcon background = new ImageIcon("C:\\Users\\Ausa\\Desktop\\扫雷\\0"+1+".png");
//	private static ImageIcon background = new ImageIcon("F:\\学习教程\\weixinfeiji\\image\\开始背景.png");

	public StartBoundary() {
		mainframe = new JFrame("飞机大战");
		newGame = new JButton("新的游戏");
		Record = new JButton("排行榜");
		mainframe.setBounds(720, 100, 500, 820);
		Record.setBounds(200, 550, 100, 30);
		newGame.setBounds(200, 500, 100, 30);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// JPanel cp = (JPanel) mainframe.getContentPane();
		// 边框布局的布局方式为东西南北中
		mainframe.setLayout(new BorderLayout()); // 设置窗体的布局为边框布局
		label.setIcon(background);
		mainframe.add(newGame);
		mainframe.add(Record);
		mainframe.add("Center", label); // 添加LABEL控件到窗体中间位置
		newGame.addActionListener(this);
		Record.addActionListener(this);
		
		// 容器监听事件
//		mainframe.addComponentListener(new ComponentAdapter() {
//			// 其中componentResized就是JFrame窗体大小改变时，触发的事件
//			public void componentResized(ComponentEvent e) {
//				mainframe.remove(label);
//				background = new ImageIcon(new ImageIcon("C:\\Users\\Ausa\\Desktop\\扫雷\01.png").getImage()
//						.getScaledInstance(mainframe.getSize().width, mainframe.getSize().height, Image.SCALE_DEFAULT));
//				label.setIcon(background);
//				// label.setHorizontalAlignment(SwingConstants.CENTER);
//				mainframe.add(label);
//			}
//		});
		mainframe.setVisible(true);
	}

	// 动作监听，判断点击的是哪个按钮
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
