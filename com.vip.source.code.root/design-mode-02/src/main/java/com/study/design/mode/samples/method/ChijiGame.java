package com.study.design.mode.samples.method;

public class ChijiGame extends Game {

	@Override
	protected void initialize() {
		System.out.println("初始化吃鸡游戏服务");
	}

	@Override
	protected void endPlay() {
		System.out.println("关闭吃鸡，保存游戏数据");
	}

	@Override
	protected void startPlay() {
		System.out.println("开始玩吃鸡");
	}

	@Override
	protected void loadRole() {
		System.out.println("加载角色——刚枪王");
	}

	@Override
	protected void loadMap() {
		System.out.println("加载雪地地图");
	}

}
