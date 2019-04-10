package RW飞机大战;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Light extends FlyingObject implements Award{
	public  int awardType;    //奖励类型
	private int speed = 2;  //移动的速度
	public Light() {
		try {
			this.image = ImageIO.read(new File("F:\\学习教程\\weixinfeiji\\image\\随机奖励.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("奖励图片捕获异常！");
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
