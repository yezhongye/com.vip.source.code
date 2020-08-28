package com.study.design.mode.samples.singleton;

public class SingletonSample {
	
	public static void main(String[] args) {
		HungerSingleton.getInstance();
		
		DlcLazySingleton.getInstance();
		
		InnerStaticClassLazySingleton.getInstance();
		
		EnumLazySingleton.INSTANCE.doSomething();
		
	}
}
