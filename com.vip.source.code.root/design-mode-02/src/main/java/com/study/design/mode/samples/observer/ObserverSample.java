package com.study.design.mode.samples.observer;

public class ObserverSample {
	public static void main(String[] args) {
		NvShenPyq pyq = new NvShenPyq();
		pyq.addObserver(new LaoWang());
		pyq.addObserver(new PgOne());
		pyq.addObserver(new HashTeacher());
		
		pyq.pushMessage("今天电脑坏了");
		System.out.println();
		pyq.pushMessage("下水道又堵了");
		System.out.println();
		pyq.pushMessage("换个发型，换种心情");
	}
}
