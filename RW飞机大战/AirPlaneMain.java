package RW飞机大战;

import java.awt.Graphics;
import java.awt.PaintContext;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AirPlaneMain {

	public static void main(String[] args) {
//		long currentTimeMillis = System.currentTimeMillis();
////		Date date = new Date();  
////		String nowTime = new SimpleDateFormat("yyyy-MM-dd").format(date);//将时间格式转换成符合Timestamp要求的格式.
////		Timestamp newdate = Timestamp.valueOf(nowTime);//把时间转换
//		Time time = new Time(currentTimeMillis);
//		System.out.println(time);
		new Connection(900);
	}
//	public static void showDestory() {
//		Thread t1=new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
////				hero.ResurrectionAction();
//				System.out.println("开始了");
//				try {
//					Thread.sleep(3000);
//					System.out.println("等待中");
////					hero.getNormal();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
////				hero.getNormal();
////				flag=true;
//				System.out.println("等待完成");
//			}
//		});
//		t1.start();
//	}
}
