package com.study.design.mode.samples.decorator;

/**
 * 被装饰者
 */
public class ConcreteComponent implements Component {
	public String methodA() {
		return "concrete-object";
	}

	public int methodB() {
		return 100;
	}
}
