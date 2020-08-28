package com.study.design.mode.samples.observer;

import java.util.ArrayList;
import java.util.List;

public class NvShenPyq implements Observable {
	private List<ObServer> fans = new ArrayList<ObServer>();
	private String msg;
	@Override
	public void addObserver(ObServer o) {
		fans.add(o);
	}

	@Override
	public void removeObserver(ObServer o) {
		fans.remove(o);
	}
	
	public void pushMessage(String msg) {
		this.msg = msg;
		System.out.println("发个朋友圈："+msg);
		notifyObservers();
	}
	
	@Override
	public void notifyObservers() {
		fans.forEach((e)->{
			e.update(msg);
		});
	}

}
