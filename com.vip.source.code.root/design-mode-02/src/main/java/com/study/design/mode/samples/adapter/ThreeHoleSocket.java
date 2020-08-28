package com.study.design.mode.samples.adapter;

public class ThreeHoleSocket {
	
	public void link(String positive, String negative, String earthWire) {
		System.out.println(String.format("多一根地线，保证安全用电：%s %s %s", positive, negative, earthWire));
	}
	
}
