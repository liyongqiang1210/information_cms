package com.maven.util.designpattern.singleton;

/**
 * 饿汉式单例模式
 * 
 * @author liyongqiang
 *
 */
public class SingletonHungry {

	private static SingletonHungry singleton = new SingletonHungry();
	
	private SingletonHungry() {
		System.out.println("我是饿汉式单例模式！我正在初始化...");
	}
	
	public static SingletonHungry getInstance() {
		
		return singleton;
	}
}
