package com.study.design.mode.samples.adapter;

/**
 * 两插头 
 *
 */
public interface TwoPlug {
	
	void power(String positive, String negative);
	
	void shutDown();
}
