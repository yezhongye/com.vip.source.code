package com.study.design.mode.samples.observer;

public class HashTeacher implements ObServer {

	@Override
	public void update(Object msg) {
		System.out.println(getClass().getSimpleName()+" 女神今天："+msg);
	}

}
