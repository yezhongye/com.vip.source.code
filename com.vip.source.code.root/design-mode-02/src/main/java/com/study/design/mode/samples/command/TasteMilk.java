package com.study.design.mode.samples.command;

public class TasteMilk implements Command {

	@Override
	public void build() {
		System.out.println("开始制作原味奶茶");
	}

}
