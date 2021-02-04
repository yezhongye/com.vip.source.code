package com.study.design.mode.samples.proxy;

public class PlayGame {

	public static void main(String[] args) {
		TuHao th = new TuHao(1.8F);
		Girl tc = new TeacherCang();
		// 静态代理
		Tony tony = new Tony();
//		tony.setGirl(tc);
//		th.dating(tony);

		System.out.println("---------------------------------------");
		// JDK 动态代理
		Girl tony1 = (Girl) TonyCompany.proxy(tc);
		th.dating(tony1);

//		Boy tcc = new TeacherChen();
//		Boy tony2 = (Boy) TonyCompany.proxy(tcc);
//		tony2.dating('E');
//		tony2.show();
//		ProxyUtils.generateClassFile(Boy.class, tony2.getClass().getName());
	}

}
