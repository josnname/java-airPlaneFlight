package RW�ɻ���ս;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

 
public class Search {
	Search(){
		JFrame search =new JFrame("���а�");
		search.setBounds(720,100, 500, 820);
		search.setLayout(null);
		font f=new font();
		f.setBounds(20, 0, 500, 70);
		font1 f1=new font1();
		f1.setBounds(20, 80, 500, 820);
		search.add(f);
		search.add(f1);
		search.setVisible(true);
		WindowAdapter l=new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				new StartBoundary();
				search.dispose();
				}
		};
		search.addWindowListener(l);
	}
	
	//��дJLABEL�ؼ�
	class font extends JLabel{
		/** ������ */
		
		public void paint(Graphics g) {
			int x = 0; // x����
			int y = 25; // y����
			Font font = new Font(Font.SANS_SERIF, Font.BOLD, 22); // ����
			g.setColor(new Color(0xFF0000));
			g.setFont(font); // ��������
			g.drawString("���а�", x+180, y); 
			y=y+35; // y������20
			x+=20;
			g.drawString("����                 ����                 ����", x, y); 
		}
	}
	class font1 extends JLabel{
		/** ������ */
		
		public void paint(Graphics g) {
			Connection c=new Connection();
			ResultSet sc=c.Connection();
			
			int x = 20; // x����
			int y = 25; // y����
			Font font = new Font(Font.SANS_SERIF, Font.BOLD, 22); // ����
			g.setColor(new Color(0xFF0000));
			g.setFont(font); // ��������
			int i=1;
			try {
				while(sc.next()) {
					String str=(i++)+"          "+sc.getString("score")+"               "
							   +sc.getString("date")+" "+
							   sc.getString("time");
					g.drawString(str, x, y); 
					y=y+35; // y������20
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		}
	}
}
