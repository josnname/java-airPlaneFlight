package RW�ɻ���ս;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bullets extends FlyingObject{
private int speed = 3;  //�ƶ����ٶ�
	
	/** ��ʼ������ */
	public Bullets(int x,int y){
		this.x = x;
		this.y = y;
		try {
			this.image = ImageIO.read(new File("F:\\ѧϰ�̳�\\weixinfeiji\\image\\bullet1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("�ӵ�ͼƬ�����쳣��");
			e.printStackTrace();
		}
	}
	public Bullets() {
		
	}
	/** �ƶ� */
	@Override
	public void step(){   
		y-=speed;
	}
	public void setStep() {
		this.speed=5;
	}
	
	public void getNormal() {
		this.speed=3;
	}
	/** Խ�紦�� */
	@Override
	public boolean outOfBounds() {
		return y<0;
	}

	@Override
	public boolean isBloodEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
