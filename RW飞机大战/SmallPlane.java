package RW�ɻ���ս;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


public class SmallPlane extends FlyingObject {
private int speed = 3;  //�ƶ�����

	/** ��ʼ������ */
	public SmallPlane(){
		try {
			this.image = ImageIO.read(new File("F:\\ѧϰ�̳�\\weixinfeiji\\image\\enemy1_fly_1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("С�ɻ�ͼƬ�����쳣��");
			e.printStackTrace();
		}
		blood =1;
		type=1;
		width = image.getWidth();
		height = image.getHeight();
		y = -height;          
		Random rand = new Random();
		x = rand.nextInt(500 - width*2);
	}
	
//	/** ��ȡ���� */
//	@Override
//	public int getScore() {  
//		return 5;
//	}

	/** //Խ�紦�� */
	@Override
	public 	boolean outOfBounds() {   
		return y>820;
	}

	/** �ƶ� */
	@Override
	public void step() {   
		y += speed;
	}

	@Override
	public boolean isBloodEmpty() {
		if(blood<=0) {
			return true;
		}
		return false;
	}

}
