package com.study.design.mode.samples.singleton;

/**
 * 双重锁定懒汉式单例
 * 
 *
 */
public class DlcLazySingleton {
	// volatile 关键字很关键
	private static volatile DlcLazySingleton instance;
	
	private DlcLazySingleton() {}
	
	public static DlcLazySingleton getInstance() {
		if(instance == null) {
			synchronized(DlcLazySingleton.class) {
				if(instance == null) {
					instance = new DlcLazySingleton();
				}
			}
		}
		return instance;
	}
	
}
