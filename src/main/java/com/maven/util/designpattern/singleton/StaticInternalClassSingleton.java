package com.maven.util.designpattern.singleton;

/**
 * Lazy initialization holder class模式
 * 这个模式综合使用了Java的类级内部类和多线程缺省同步锁的知识，很巧妙地同时实现了延迟加载和线程安全。
 * 
 * @author liyongqiang
 *
 */
public class StaticInternalClassSingleton {

	private StaticInternalClassSingleton() {
		System.out.println("我是Lazy initialization holder class模式的单例模式,我正在初始化...");
	}

	/**
	 * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例 没有绑定关系，而且只有被调用到时才会装载，从而实现了延迟加载。
	 */
	private static class SingletonHolder {
		/**
		 * 静态初始化器，由JVM来保证线程安全
		 * 
		 */
		private static StaticInternalClassSingleton instance = new StaticInternalClassSingleton();
	}

	public static StaticInternalClassSingleton getInstance() {
		
		return SingletonHolder.instance;
	}
}
