package com.study.design.mode.samples.command;

public class SilkMilk implements Command {

	@Override
	public void build() {
		System.out.println("开始制作丝袜奶茶");
	}

}
