package com.study.design.mode.samples.adapter;

public class AdapterSample {
	public static void main(String[] args) {
		// 不需要把墙上的插座重新安装，也不需要重新买个三插孔的电风扇
		ThreeHoleSocket socket = new ThreeHoleSocket();
		Fanner fanner = new Fanner();
		
		// 使用插线板适配器
		PlugBoardAdapter adapter = new PlugBoardAdapter();
		adapter.linkSocket(socket);
		fanner.setPlug(adapter);
		fanner.run();
	}
}
