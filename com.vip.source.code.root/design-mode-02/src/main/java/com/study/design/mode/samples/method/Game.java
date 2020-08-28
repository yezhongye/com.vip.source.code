package com.study.design.mode.samples.method;

public abstract class Game {
	
	public final void play() {
		// 初始化游戏
		initialize();
		// 加载地图
		loadMap();
		// 加载角色
		loadRole();
		// 开始游戏
		startPlay();
		// 结束游戏
		endPlay();
	}

	protected abstract void initialize();
	
	protected abstract void endPlay();

	protected abstract void startPlay();

	protected abstract void loadRole();

	protected abstract void loadMap();

	
}
