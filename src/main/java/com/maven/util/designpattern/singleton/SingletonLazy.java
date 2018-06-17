package com.maven.util.designpattern.singleton;

/**
 * 懒汉式单例模式
 * 
 * @author liyongqiang
 *
 */
public class SingletonLazy {

	private static SingletonLazy singletonLazy;

	private SingletonLazy() {
		System.out.println("我是懒汉式单例模式,我正在初始化...");
	};

	public static synchronized SingletonLazy getInstance() {

		if (singletonLazy == null) {
			singletonLazy = new SingletonLazy();
		}

		return singletonLazy;

	}
}
