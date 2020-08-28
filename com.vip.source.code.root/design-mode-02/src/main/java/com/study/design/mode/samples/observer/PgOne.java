package com.study.design.mode.samples.observer;

public class PgOne implements ObServer {

	@Override
	public void update(Object msg) {
		System.out.println(getClass().getSimpleName()+" 收到通知："+msg);
	}

}
