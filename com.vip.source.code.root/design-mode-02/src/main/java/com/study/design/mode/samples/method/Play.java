package com.study.design.mode.samples.method;

public class Play {
	public static void main(String[] args) {
		Game wzry = new WangzheRongyaoGame();
		wzry.play();
		
		System.out.println();
		Game cj = new ChijiGame();
		cj.play();
	}
}
