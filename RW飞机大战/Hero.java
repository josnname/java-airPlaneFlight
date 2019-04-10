package RW�ɻ���ս;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Ӣ�ۻ�:�Ƿ�����
 */
public class Hero extends FlyingObject{
	boolean Resurrection=false; //�Ƿ񸴻�
	private BufferedImage[] images = {};  //Ӣ�ۻ�ͼƬ
	private int index = 0;                //Ӣ�ۻ�ͼƬ�л�����
	BufferedImage hero0,hero1,fuhuo;
	private int doubleFire;   //˫������
	private int life;   //��
	public void ResurrectionAction() {
		x = 200;
		y = 690;
	}
	public void getNormal() {
//		this.image=hero1;
		Resurrection=false;
	}
	/** ��ʼ������ */
	public Hero(){
		try {
			fuhuo=ImageIO.read(new File("F:\\ѧϰ�̳�\\weixinfeiji\\image\\����.png"));
			hero0 = ImageIO.read(new File("F:\\ѧϰ�̳�\\weixinfeiji\\image\\hero0 .png"));
			hero1 = ImageIO.read(new File("F:\\ѧϰ�̳�\\weixinfeiji\\image\\hero1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("��ɻ�ͼƬ�����쳣��");
			e.printStackTrace();
		}
		life = 3;   //��ʼ3����
		doubleFire = 0;   //��ʼ����Ϊ0
		images = new BufferedImage[]{hero0, hero1}; //Ӣ�ۻ�ͼƬ����
		image = hero0;   //��ʼΪhero0ͼƬ
		width = image.getWidth();
		height = image.getHeight();
		x = 200;
		y = 690;
	}
	
//	/** ��ȡ˫������ */
//	public int isDoubleFire() {
//		return doubleFire;
//	}

	/** ����˫������ */
	public void setDoubleFire(int doubleFire) {
		this.doubleFire = doubleFire;
	}
	
	/** ���ӻ��� */
	public void addDoubleFire(){
		doubleFire = 40;
	}
	
	/** ���� */
	public void addLife(){  //����
		life+=1;
	}
	
	/** ���� */
	public void subtractLife(){   //����
		life--;
	}
	
	/** ��ȡ�� */
	public int getLife(){
		return life;
	}
	
	/** ��ǰ�����ƶ���һ�£���Ծ��룬x,y���λ��  */
	public void moveTo(int x,int y){   
//		this.x = x - width/2;
//		this.y = y - height/2;
		this.x=this.x+x;
		this.y=this.y+y;
	}

	/** Խ�紦�� */
	@Override
	public boolean outOfBounds() {
		return false;  
	}

	/** �����ӵ� */
	public Bullets[] shoot(){   
		int xStep = width/4;      //4��
		int yStep = 20;  //��
		if(doubleFire>0){  //˫������
			Bullets[] bullets = new Bullets[2];
			bullets[0] = new Bullets(x+xStep,y-yStep);  //y-yStep(�ӵ���ɻ���λ��)
			bullets[1] = new Bullets(x+3*xStep,y-yStep);
			return bullets;
		}else{      //��������
			Bullets[] bullets = new Bullets[1];
			bullets[0] = new Bullets(x+2*xStep,y-yStep);  
			return bullets;
		}
	}

	/** �ƶ� */
	@Override
	public void step() {
		if(Resurrection) {
			image =fuhuo;
		}
		else if(images.length>0){
			image = images[index++/10%images.length];  //�л�ͼƬhero0��hero1
		}
	}
	
	/** ��ײ�㷨 */
	public boolean hit(FlyingObject other){
		
		int x1 = other.x - this.width/2;                 //x������С����
		int x2 = other.x + this.width/2 + other.width;   //x����������
		int y1 = other.y - this.height/2;                //y������С����
		int y2 = other.y + this.height/2 + other.height; //y����������
	
		int herox = this.x + this.width/2;               //Ӣ�ۻ�x�������ĵ����
		int heroy = this.y + this.height/2;              //Ӣ�ۻ�y�������ĵ����
		
		return herox>x1 && herox<x2 && heroy>y1 && heroy<y2;   //���䷶Χ��Ϊײ����
	}

	@Override
	public boolean isBloodEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
