package com.study.design.mode.samples.decorator;

public class DecoratorSample {
	public static void main(String[] args) {
		// 被装饰者，需要基于它进行扩展，当然装饰者也可以成为被装饰者
		Component cc = new ConcreteComponent();
		// 装饰者
		Component c = new Decorator(cc);
		// 装饰者成为被装饰者，通过这种方式可以装饰无数个被装饰者
		Component d = new DecoratorA(c);
		// 客户端只看到了Component
		System.out.println(d.methodA());
		System.out.println(d.methodB());
	}
}
