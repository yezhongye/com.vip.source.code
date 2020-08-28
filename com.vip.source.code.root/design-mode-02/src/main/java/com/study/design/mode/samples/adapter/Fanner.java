package com.study.design.mode.samples.adapter;

public class Fanner {
	private TwoPlug plug;
	
	public void run() {
		plug.power("1", "0");
		System.out.println("获得动力");
		System.out.println("风扇运转");
		plug.shutDown();
		System.out.println("关闭电力，风扇停止运转");
	}
	
	public void setPlug(TwoPlug p) {
		plug = p;
	}
}
