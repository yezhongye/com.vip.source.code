package com.study.design.mode.samples.adapter;

/**
 * 插线板对类适配器实现。 提供两孔、三孔插座。
 * <br>
 * 无法将ThreeHoleSocket的子类实现替换进来。
 */
public class PlugBoardAdapter2 extends ThreeHoleSocket implements TwoPlug {
	private String ew = "PlugBoardAdapter2's earthWire";	// 私有内置地线
	
	public void linkSocket() {
	}
	
	@Override
	public void power(String positive, String negative) {
		link(positive, negative, ew);
	}

	@Override
	public void shutDown() {
		System.out.println("拔掉插线板插头");
	}
	
	public void power(String positive,String negative,String ew) {
		link(positive, negative, ew);
	}
}
