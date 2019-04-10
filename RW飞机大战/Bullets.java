package RW飞机大战;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bullets extends FlyingObject{
private int speed = 3;  //移动的速度
	
	/** 初始化数据 */
	public Bullets(int x,int y){
		this.x = x;
		this.y = y;
		try {
			this.image = ImageIO.read(new File("F:\\学习教程\\weixinfeiji\\image\\bullet1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("子弹图片捕获异常！");
			e.printStackTrace();
		}
	}
	public Bullets() {
		
	}
	/** 移动 */
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
	/** 越界处理 */
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
