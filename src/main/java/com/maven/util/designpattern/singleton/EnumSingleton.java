package com.maven.util.designpattern.singleton;

/**
 * 枚举单例型单例模式
 * 
 * @author liyongqiang
 *
 */
public enum EnumSingleton {

	INSTANCE;

	private Resource instance;

	private EnumSingleton() {
		instance = new Resource();
	}

	public Resource getInstance() {
		return instance;
	}
}

class Resource {

}
