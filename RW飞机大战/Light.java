package RW�ɻ���ս;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Light extends FlyingObject implements Award{
	public  int awardType;    //��������
	private int speed = 2;  //�ƶ����ٶ�
	public Light() {
		try {
			this.image = ImageIO.read(new File("F:\\ѧϰ�̳�\\weixinfeiji\\image\\�������.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("����ͼƬ�����쳣��");
			e.printStackTrace();
		}
		width = image.getWidth();
		height = image.getHeight();
		y = 0;
		Random rand = new Random();
		x = rand.nextInt(500 - width);
		Random rm=new Random();
		this.awardType=rm.nextInt(3);
	}
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return awardType;
	}

	@Override
	public boolean isBloodEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		if(y>821) {
			return true;
		}
		return false;
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		y+=speed;
	}

}
