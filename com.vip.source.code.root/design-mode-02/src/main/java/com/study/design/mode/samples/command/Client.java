package com.study.design.mode.samples.command;

import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		// waiter();
		waitress();
	}
	
	public static void waiter() {
		Waiter waiter = new Waiter();
		waiter.showMenu();
		Scanner scanner = new Scanner(System.in);
		System.out.println("请选择：");
		// 发送内容
		String command = scanner.nextLine();
		waiter.receiver(command);
		scanner.close();
	}
	
	public static void waitress() {
		Waitress waiter = new Waitress();
		waiter.register("原味奶茶", new TasteMilk());
		waiter.register("丝袜奶茶", new SilkMilk());
		waiter.register("木瓜奶茶", new PawpawMilk());
		waiter.register("大杯木瓜奶茶", new PawpawMilk());
		
		waiter.showMenu();
		Scanner scanner = new Scanner(System.in);
        System.out.println("请选择：");
        // 发送内容
        String command = scanner.nextLine();
		waiter.receiver(command);
		scanner.close();
	}
	
	
}
