package com.study.design.mode.samples.observer;

public interface Observable {
	void addObserver(ObServer o);
	void removeObserver(ObServer o);
	void notifyObservers();
}
