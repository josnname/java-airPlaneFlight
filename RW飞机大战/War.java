package RW飞机大战;

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
	int STATE=1;  //用来记录游戏状态
	int SCORE=0;
	boolean flag=true;
	static JFrame frame;
	Hero hero=new Hero();
	int heroXY=10;
	private static Thread t1;
	private Timer timer; // 定时器
	private int intervel = 1000/ 100 ; // 时间间隔(毫秒)
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
	static { // 静态代码块，初始化图片资源
		try {
			pause =ImageIO.read(new File("F:\\学习教程\\weixinfeiji\\image\\pause.png"));
			gameover = ImageIO.read(new File("F:\\学习教程\\weixinfeiji\\image\\gameover.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void frame1(War game) {
		frame = new JFrame("Fly");
		frame.setBounds(720,100, 500, 820);
		
		ceng=new JLayeredPane();
		ImageIcon backgroundp=new ImageIcon(new ImageIcon("F:\\学习教程\\weixinfeiji\\image\\background_1.png")
				.getImage().getScaledInstance(frame.getSize().width, 
						frame.getSize().height,Image.SCALE_DEFAULT ));
		JLabel background=new JLabel();
		background.setIcon(backgroundp);
		background.setBounds(0, 0, 500, 820);
		game.setBounds(0, 0, 500, 820);
		game.setOpaque(false);  //设置该层透明
		ceng.add(background, new Integer(200));
		ceng.add(game, new Integer(300));
		frame.add(ceng);
		game.action();
		frame.setVisible(true); // 尽快调用paint
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
			public void mouseClicked(MouseEvent e) { // 鼠标进入
				if (STATE != 0) { // 暂停状态下运行
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
					//向下箭头   
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
		timer = new Timer(); // 主流程控制
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (STATE ==1) { // 运行状态
					enterAction(); // 飞行物入场
					stepAction(); // 走一步
					shootAction(); // 英雄机射击
					bangAction(); // 子弹打飞行物
					outOfBoundsAction(); // 删除越界飞行物及子弹
					hitAction();
					if(hero.Resurrection  && flag) {
						flag=false;
						
						showDestory();
					}
					checkGameOverAction(); // 检查游戏结束
				}
				repaint(); // 重绘，调用paint()方法
			}

		}, intervel, intervel);
//		
	}
	/**
	 * 英雄机进入狂暴状态
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
		paintHero(g); // 画英雄机
		paintBullets(g); // 画子弹
		paintFlyingObjects(g); // 画飞行物
		paintDestoryObjects(g); //画毁坏的画面
		paintScore(g); // 画分数
		paintState(g); // 画游戏状态
	}
	/** 画分数 */
	public void paintScore(Graphics g) {
		int x = 10; // x坐标
		int y = 25; // y坐标
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 15); // 字体
		g.setColor(new Color(0xFF0000));
		g.setFont(font); // 设置字体
		g.drawString("SCORE:" + SCORE, x, y); // 画分数
		y=y+20; // y坐标增20
		g.drawString("LIFE:" + hero.getLife(), x, y); // 画命
	}
	public void paintHero(Graphics g) {
		g.drawImage(hero.getImage(), hero.getX(), hero.getY(), null);
	}
	/** 画飞行物 */
	public void paintFlyingObjects(Graphics g) {
		for (int i = 0; i < flyings.size(); i++) {
			FlyingObject f = flyings.get(i);
			g.drawImage(f.getImage(), f.getX(), f.getY(), null);
		}
	}
	/**
	 * 画子弹
	 * @param g
	 */
	public void paintBullets(Graphics g) {
		for (int i = 0; i < bullets.size(); i++) {
			Bullets b = bullets.get(i);
			g.drawImage(b.getImage(), b.getX() - b.getWidth() / 2, b.getY(),
					null);
		}
	}
	/** 画游戏状态 */
	public void paintState(Graphics g) {
		switch (STATE) {
		case 0: // 暂停状态
			g.drawImage(pause, 0, 0, null);
			break;
		case -1: // 游戏终止状态
			g.drawImage(gameover, 0, 0, null);
			break;
		}
	}
	long shootIndex = 0;
	public void shootAction() {
		shootIndex++;
		if (shootIndex % 30 == 0) { // 300毫秒发一颗
			Bullets[] bs = hero.shoot(); // 英雄打出子弹
			for(int i=0 ;i<bs.length ;i++) {
				bullets.add(bs[i]);
			}
		}
	}

	/** 删除越界飞行物及子弹 */
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
		for (int i = 0; i < flyings.size(); i++) { // 飞行物走一步
			FlyingObject f = flyings.get(i);
			f.step();
		}

		for (int i = 0; i < bullets.size(); i++) { // 子弹走一步
			Bullets b = bullets.get(i);
			b.step();
		}
			hero.step();
	}
	long flyEnteredIndex = 0; // 飞行物入场计数

	/** 飞行物入场 */
	public void enterAction() {
		flyEnteredIndex++;
		if (flyEnteredIndex % 50 == 0) { // 500毫秒生成一个飞行物--10*40
			flyEnteredIndex=1;
			flyings.add(nextOne());
		}
	}
	/**
	 * 碰撞事件检测
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
						hero.setDoubleFire(0); //解除双倍火力
						if(hero.getLife()<=0) {
							STATE=-1;   //生命值用完。游戏结束
						}
					}else {
						flyings.remove(obj);
						Award a=(Award)obj;
						if(a.getType()==0) {
							hero.addDoubleFire(); //获得双倍火力
						}
						if(a.getType()==1) {
							hero.addLife();  //获得生命值+1；
						}
						if(a.getType()==2) {
							if(!isMusth) {
								musth();  //获得狂暴状态5秒
								isMusth=true;
							}
						}
					}
				}
			}
		}
	}
	/**
	 * 随机生成飞行物
	 * 
	 * @return 飞行物对象
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
	/** 子弹与飞行物碰撞检测 */
	public void bangAction() {
		for (int i = 0; i < bullets.size(); i++) { // 遍历所有子弹
			Bullets b = bullets.get(i);
			bang(b); // 子弹和飞行物之间的碰撞检查
		}
	}
	/** 子弹和飞行物之间的碰撞检查 */
	public void bang(Bullets bullet) {
		for (int i = 0; i < flyings.size(); i++) {
			FlyingObject obj = flyings.get(i);
			
			if (obj.shootBy(bullet)) { // 判断是否击中
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
				obj.image = ImageIO.read(new File("F:\\学习教程\\weixinfeiji\\image\\中飞机爆炸.gif"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("中飞机图片捕获异常！");
				e.printStackTrace();
			}
			return obj;
		}
		else if(type ==1) {
			try {
				obj.image = ImageIO.read(new File("F:\\学习教程\\weixinfeiji\\image\\小飞机爆炸.gif"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("中飞机图片捕获异常！");
				e.printStackTrace();
			}
			return obj;
		}
		else if(type==4) {
			try {
				obj.image = ImageIO.read(new File("F:\\学习教程\\weixinfeiji\\image\\大飞机爆炸.gif"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("中飞机图片捕获异常！");
				e.printStackTrace();
			}
			return obj;
		}
		return obj;
	}

}
