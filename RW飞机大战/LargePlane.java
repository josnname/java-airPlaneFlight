package RW飞机大战;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class LargePlane extends FlyingObject{
private int speed = 1;  //移动步骤
	/** 初始化数据 */
	public LargePlane(){
		try {
			this.image = ImageIO.read(new File("F:\\学习教程\\weixinfeiji\\image\\enemy2_fly_1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("大飞机图片捕获异常！");
			e.printStackTrace();
		}
		blood =4;
		type=4;
		width = image.getWidth();
		height = image.getHeight();
		y = -height;          
		Random rand = new Random();
		x = rand.nextInt(500 - width);
	}
	
//	/** 获取分数 */
//	@Override
//	public int getScore() {  
//		return 5;
//	}

	/** //越界处理 */
	@Override
	public 	boolean outOfBounds() {   
		return y>820;
	}

	/** 移动 */
	@Override
	public void step() {   
		y += speed;
	}

	@Override
	public boolean isBloodEmpty() {
		if(this.blood<=0) {
			return true;
		}
		return false;
	}

}
