package com.study.design.mode.samples.observer;

public class LaoWang implements ObServer {

	@Override
	public void update(Object msg) {
		System.out.println(getClass().getSimpleName()+" 隔壁女神："+msg);
	}

}
