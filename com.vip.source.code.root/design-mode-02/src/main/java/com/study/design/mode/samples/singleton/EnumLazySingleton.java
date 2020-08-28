package com.study.design.mode.samples.singleton;

/**
 * 枚举其实是一个特殊的Java类，
 * 仍然是通过JVM类加载机制保证线程安全
 * 枚举实现的单例，简单强大。
 */
public enum EnumLazySingleton {
	INSTANCE;
	
	// 需要使用的方法
	public void doSomething() {
	}
}
