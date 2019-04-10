package RW�ɻ���ս;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class War extends JPanel {
	int STATE=1;  //������¼��Ϸ״̬
	int SCORE=0;
	boolean flag=true;
	static JFrame frame;
	Hero hero=new Hero();
	int heroXY=10;
	private static Thread t1;
	private Timer timer; // ��ʱ��
	private int intervel = 1000/ 100 ; // ʱ����(����)
	private List<FlyingObject>  destroyed =new LinkedList<FlyingObject>();
	private List<FlyingObject>  flyings =new LinkedList<FlyingObject>();
	private List<Bullets>  bullets =new LinkedList<Bullets>();
	static JLayeredPane ceng;
	public static BufferedImage pause;
	public static BufferedImage gameover;
	Boolean isMusth=false;
	
	public static void main(String[] args) {
//		NewGame isgame =new NewGame();
//		new StartBoundary(isgame);
//		War game =new War();
		new StartBoundary();
	}
	static { // ��̬����飬��ʼ��ͼƬ��Դ
		try {
			pause =ImageIO.read(new File("F:\\ѧϰ�̳�\\weixinfeiji\\image\\pause.png"));
			gameover = ImageIO.read(new File("F:\\ѧϰ�̳�\\weixinfeiji\\image\\gameover.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void frame1(War game) {
		frame = new JFrame("Fly");
		frame.setBounds(720,100, 500, 820);
		
		ceng=new JLayeredPane();
		ImageIcon backgroundp=new ImageIcon(new ImageIcon("F:\\ѧϰ�̳�\\weixinfeiji\\image\\background_1.png")
				.getImage().getScaledInstance(frame.getSize().width, 
						frame.getSize().height,Image.SCALE_DEFAULT ));
		JLabel background=new JLabel();
		background.setIcon(backgroundp);
		background.setBounds(0, 0, 500, 820);
		game.setBounds(0, 0, 500, 820);
		game.setOpaque(false);  //���øò�͸��
		ceng.add(background, new Integer(200));
		ceng.add(game, new Integer(300));
		frame.add(ceng);
		game.action();
		frame.setVisible(true); // �������paint
	}
	public void checkGameOverAction() {
		if (STATE==-1) {
			timer.cancel();
			new Connection(SCORE);
		}
	}
	public void action() {
		MouseAdapter l2=new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // ������
				if (STATE != 0) { // ��ͣ״̬������
					STATE=0;
				}
				else {
					STATE=1;
				}
			}
		};
		WindowAdapter l=new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
					
					frame.dispose();
				}
		};
		KeyAdapter l1=new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_UP) {
					
					if(!(hero.getY()<0)) {
						hero.moveTo(0,-heroXY);
					} 
					
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) { 
					//���¼�ͷ   
					if(!(hero.y>690)) {
						hero.moveTo(0, heroXY);
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if(!(hero.x<0)) {
						hero.moveTo(-heroXY, 0);
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if(!(hero.x>420)) {
						hero.moveTo(heroXY, 0);
					}
				}
			}
		};
		frame.addMouseListener(l2);
		frame.addWindowListener(l);
		frame.addKeyListener(l1);
		timer = new Timer(); // �����̿���
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (STATE ==1) { // ����״̬
					enterAction(); // �������볡
					stepAction(); // ��һ��
					shootAction(); // Ӣ�ۻ����
					bangAction(); // �ӵ��������
					outOfBoundsAction(); // ɾ��Խ������Ｐ�ӵ�
					hitAction();
					if(hero.Resurrection  && flag) {
						flag=false;
						
						showDestory();
					}
					checkGameOverAction(); // �����Ϸ����
				}
				repaint(); // �ػ棬����paint()����
			}

		}, intervel, intervel);
//		
	}
	/**
	 * Ӣ�ۻ������״̬
	 */
	public void musth() {
		t1=new Thread(new Runnable() {
			@Override
			public void run() {
				heroXY+=10;
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				heroXY=10;
				isMusth=false;
			}
		});
		t1.start();
	}
	public void showDestory() {
		t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				hero.ResurrectionAction();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				hero.getNormal();
				flag=true;
			}
		});
		t1.start();
	}
	public void paint(Graphics g) {
		paintHero(g); // ��Ӣ�ۻ�
		paintBullets(g); // ���ӵ�
		paintFlyingObjects(g); // ��������
		paintDestoryObjects(g); //���ٻ��Ļ���
		paintScore(g); // ������
		paintState(g); // ����Ϸ״̬
	}
	/** ������ */
	public void paintScore(Graphics g) {
		int x = 10; // x����
		int y = 25; // y����
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 15); // ����
		g.setColor(new Color(0xFF0000));
		g.setFont(font); // ��������
		g.drawString("SCORE:" + SCORE, x, y); // ������
		y=y+20; // y������20
		g.drawString("LIFE:" + hero.getLife(), x, y); // ����
	}
	public void paintHero(Graphics g) {
		g.drawImage(hero.getImage(), hero.getX(), hero.getY(), null);
	}
	/** �������� */
	public void paintFlyingObjects(Graphics g) {
		for (int i = 0; i < flyings.size(); i++) {
			FlyingObject f = flyings.get(i);
			g.drawImage(f.getImage(), f.getX(), f.getY(), null);
		}
	}
	/**
	 * ���ӵ�
	 * @param g
	 */
	public void paintBullets(Graphics g) {
		for (int i = 0; i < bullets.size(); i++) {
			Bullets b = bullets.get(i);
			g.drawImage(b.getImage(), b.getX() - b.getWidth() / 2, b.getY(),
					null);
		}
	}
	/** ����Ϸ״̬ */
	public void paintState(Graphics g) {
		switch (STATE) {
		case 0: // ��ͣ״̬
			g.drawImage(pause, 0, 0, null);
			break;
		case -1: // ��Ϸ��ֹ״̬
			g.drawImage(gameover, 0, 0, null);
			break;
		}
	}
	long shootIndex = 0;
	public void shootAction() {
		shootIndex++;
		if (shootIndex % 30 == 0) { // 300���뷢һ��
			Bullets[] bs = hero.shoot(); // Ӣ�۴���ӵ�
			for(int i=0 ;i<bs.length ;i++) {
				bullets.add(bs[i]);
			}
		}
	}

	/** ɾ��Խ������Ｐ�ӵ� */
	public void outOfBoundsAction() {
		for (int i = 0; i < flyings.size(); i++) {
			FlyingObject f = flyings.get(i);
			if (f.outOfBounds()) {
				flyings.remove(i);
			}
		}
		for (int i = 0; i < bullets.size(); i++) {
			Bullets b = bullets.get(i);
			if (b.outOfBounds()) {
				bullets.remove(i);
			}
		}
	}
	public void stepAction() {
		for (int i = 0; i < flyings.size(); i++) { // ��������һ��
			FlyingObject f = flyings.get(i);
			f.step();
		}

		for (int i = 0; i < bullets.size(); i++) { // �ӵ���һ��
			Bullets b = bullets.get(i);
			b.step();
		}
			hero.step();
	}
	long flyEnteredIndex = 0; // �������볡����

	/** �������볡 */
	public void enterAction() {
		flyEnteredIndex++;
		if (flyEnteredIndex % 50 == 0) { // 500��������һ��������--10*40
			flyEnteredIndex=1;
			flyings.add(nextOne());
		}
	}
	/**
	 * ��ײ�¼����
	 */
	public void hitAction() {
		if(!(hero.Resurrection)) {
			for(int i=0;i<flyings.size();i++) {
				FlyingObject obj = flyings.get(i);
				if(hero.hit(obj)) {
					if(!(obj instanceof Award)) {
						flyings.remove(obj);
						hero.subtractLife();
						hero.Resurrection=true;
						hero.setDoubleFire(0); //���˫������
						if(hero.getLife()<=0) {
							STATE=-1;   //����ֵ���ꡣ��Ϸ����
						}
					}else {
						flyings.remove(obj);
						Award a=(Award)obj;
						if(a.getType()==0) {
							hero.addDoubleFire(); //���˫������
						}
						if(a.getType()==1) {
							hero.addLife();  //�������ֵ+1��
						}
						if(a.getType()==2) {
							if(!isMusth) {
								musth();  //��ÿ�״̬5��
								isMusth=true;
							}
						}
					}
				}
			}
		}
	}
	/**
	 * ������ɷ�����
	 * 
	 * @return ���������
	 */
	public static FlyingObject nextOne() {
		Random random = new Random();
		int type = random.nextInt(20);
		if(type==19) {
			return new Light();
		}
		if (type < 9) {
			return new SmallPlane();
			
		}
		if(type<15) {
			return new MedPlane();
		}
		else {
			return new LargePlane();
		}
	}
	/** �ӵ����������ײ��� */
	public void bangAction() {
		for (int i = 0; i < bullets.size(); i++) { // ���������ӵ�
			Bullets b = bullets.get(i);
			bang(b); // �ӵ��ͷ�����֮�����ײ���
		}
	}
	/** �ӵ��ͷ�����֮�����ײ��� */
	public void bang(Bullets bullet) {
		for (int i = 0; i < flyings.size(); i++) {
			FlyingObject obj = flyings.get(i);
			
			if (obj.shootBy(bullet)) { // �ж��Ƿ����
				if(!(obj instanceof Award)) {
					bullets.remove(bullet);
					obj.blood-=1;
					if(obj.isBloodEmpty()) {
						flyings.remove(i);
						destroyed.add(obj);
						if(obj.type==2) {
							SCORE+=10;
						}
						if(obj.type==1) {
							SCORE+=5;
						}else {
							SCORE+=20;
						}
						break;
					}
				}
			}
		}
	}
	public void paintDestoryObjects(Graphics g) {
		while(destroyed.size()!=0) {
			FlyingObject obj=destroyed.get(0);
			obj=this.destroyType(obj);
			g.drawImage(obj.getImage(), obj.getX(), obj.getY(), null);
			destroyed.remove(0);
		}
	}
	public FlyingObject destroyType(FlyingObject obj) {
		int type=obj.type;
		if(type==2) {
			try {
				obj.image = ImageIO.read(new File("F:\\ѧϰ�̳�\\weixinfeiji\\image\\�зɻ���ը.gif"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("�зɻ�ͼƬ�����쳣��");
				e.printStackTrace();
			}
			return obj;
		}
		else if(type ==1) {
			try {
				obj.image = ImageIO.read(new File("F:\\ѧϰ�̳�\\weixinfeiji\\image\\С�ɻ���ը.gif"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("�зɻ�ͼƬ�����쳣��");
				e.printStackTrace();
			}
			return obj;
		}
		else if(type==4) {
			try {
				obj.image = ImageIO.read(new File("F:\\ѧϰ�̳�\\weixinfeiji\\image\\��ɻ���ը.gif"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("�зɻ�ͼƬ�����쳣��");
				e.printStackTrace();
			}
			return obj;
		}
		return obj;
	}

}
