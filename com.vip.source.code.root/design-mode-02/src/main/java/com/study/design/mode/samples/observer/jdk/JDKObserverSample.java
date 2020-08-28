package com.study.design.mode.samples.observer.jdk;

import java.util.Observable;
import java.util.Observer;

/**
 * JDK Observable与Observer的介绍。<br/>
 * <ul>
 * Observable存在的问题：
 * <li>Observable是一个类，也没有实现接口。
 * <li>主题必须继承自它，如果主题想继承另外的类，这会是一个问题。限制它的复用潜力。
 * </ul>
 *
 */
public class JDKObserverSample {

	public static void main(String[] args) {
		Observable subject1 = new Observable() {
			public synchronized void notifyObservers(Object data) {
				setChanged();
				super.notifyObservers(data);
			}
		};

		subject1.addObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				System.out.println("观察者1收到通知被更新了..." + arg);
			}
		});

		subject1.addObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				System.out.println("观察者2收到通知被更新了..." + arg);
			}
		});

		subject1.notifyObservers("change1");
		subject1.notifyObservers("change2");
	}
}
