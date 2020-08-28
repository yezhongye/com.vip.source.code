package com.study.design.mode.samples.command;

/**
 * 服务生招待客人
 *
 */
public class Waiter {
	
	/**
	 * 客人点单
	 * @param command
	 */
	public void receiver(String command) {
		System.out.println("您选择了："+command);
		switch(command) {
		case "烧仙草":
			new ShaoxiancaoMilk().build();
			break;
		case "苹果奶茶":
			new AppleMilk().build();
			break;
		case "草莓奶茶":
			new StrawberryMilk().build();
			break;
		default :
			System.out.println("没有这样的品种");
			break;
		}
		// 如果再扩展奶茶品种，就具有了不同的做法，需要修改这里的代码
		
	}
	
	public void showMenu() {
		System.out.println("老板你好，本有以下奶茶：");
		System.out.println("\t烧仙草");
		System.out.println("\t苹果奶茶");
		System.out.println("\t草莓奶茶");
	}
	
	
}
