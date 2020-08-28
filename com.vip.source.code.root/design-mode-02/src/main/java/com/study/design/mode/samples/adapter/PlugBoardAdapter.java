package com.study.design.mode.samples.adapter;

/**
 * 插线板对象适配器。
 * 提供两孔、三孔插座。
 * 
 */
public class PlugBoardAdapter implements TwoPlug {
	private ThreeHoleSocket powerSocket;
	private String ew = "PlugBoardAdapter's earthWire";	// 私有内置地线
	
	public void linkSocket(ThreeHoleSocket s) {
		powerSocket = s;
	}
	
	@Override
	public void power(String positive, String negative) {
		powerSocket.link(positive, negative, ew);
	}

	@Override
	public void shutDown() {
		System.out.println("拔掉插线板插头");
	}
	
	public void power(String positive,String negative,String ew) {
		powerSocket.link(positive, negative, ew);
	}
}
