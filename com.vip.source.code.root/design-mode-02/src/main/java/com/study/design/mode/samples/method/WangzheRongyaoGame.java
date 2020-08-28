package com.study.design.mode.samples.method;

public class WangzheRongyaoGame extends Game {

	@Override
	protected void initialize() {
		System.out.println("初始化王者荣耀游戏服务");
	}

	@Override
	protected void endPlay() {
		System.out.println("关闭王者荣耀，保存游戏数据");
	}

	@Override
	protected void startPlay() {
		System.out.println("开始玩王者荣耀");
	}

	@Override
	protected void loadRole() {
		System.out.println("加载角色——李白");
	}

	@Override
	protected void loadMap() {
		System.out.println("加载王者峡谷地图");
	}

}
