package com.maven.util.designpattern.factory;

/**
 * 简单工厂模式
 * 
 * @author liyongqiang
 *
 */
public class SimpleFactory {
	public static void main(String[] args) {
		ComputerEngineer ce = new ComputerEngineer();
		ce.makeComputer(1, 1);
	}
}

/**
 * cpu 接口
 * 
 * @author liyongqiang
 *
 */
interface Cpu {
	public void calculate();
}

/**
 * Intel CPU
 * 
 * @author liyongqiang
 *
 */
class IntelCpu implements Cpu {

	private int pins; // CPU针脚数

	public IntelCpu(int pins) {
		this.pins = pins;
	}

	public void calculate() {
		System.out.println("Intel CPU的针脚数 ：" + pins);
	}

}

/**
 * AMD CPU
 * 
 * @author liyongqiang
 *
 */
class AmdCpu implements Cpu {

	private int pins;

	public AmdCpu(int pins) {
		this.pins = pins;
	}

	public void calculate() {
		System.out.println("AMD CPU的针脚数 ：" + pins);
	}

}

/**
 * 主板接口
 * 
 * @author liyongqiang
 *
 */
interface MainBoard {
	public void installCPU();
}

/**
 * Intel 主板
 * 
 * @author liyongqiang
 *
 */
class IntelMainBoard implements MainBoard {

	private int cpuHoles; // CPU插槽的孔数

	public IntelMainBoard(int cpuHoles) {
		this.cpuHoles = cpuHoles;
	}

	public void installCPU() {
		System.out.println("Intel 主板CPU插槽孔数 ：" + cpuHoles);
	}

}

/**
 * AMD 主板
 * 
 * @author liyongqiang
 *
 */
class AmdMainBoard implements MainBoard {

	private int cpuHoles;

	public AmdMainBoard(int cpuHoles) {
		this.cpuHoles = cpuHoles;
	}

	public void installCPU() {
		System.out.println("AMD 主板CPU插槽孔数 ：" + cpuHoles);
	}

}

/**
 * cpu工厂类
 * 
 * @author liyongqiang
 *
 */
class CpuFactory {
	public static Cpu createCpu(int type) {
		Cpu cpu = null;
		if (type == 1) {
			cpu = new IntelCpu(775);
		} else if (type == 2) {
			cpu = new AmdCpu(938);
		}
		return cpu;
	}
}

/**
 * 主板工厂类
 * 
 * @author liyongqiang
 *
 */
class MainBoardFactory {
	public static MainBoard createMainBorad(int type) {
		MainBoard mainBoard = null;
		if (type == 1) {
			mainBoard = new IntelMainBoard(775);
		} else if (type == 2) {
			mainBoard = new AmdMainBoard(938);
		}
		return mainBoard;
	}
}

/**
 * 装机工程师类
 * 
 * @author liyongqiang
 *
 */
class ComputerEngineer {

	private Cpu cpu = null; // 安装电脑所需的cpu
	private MainBoard mainBoard = null; // 安装电脑所需的主板

	public void makeComputer(int cpuType, int mainBoardType) {
		prepareHardwares(cpuType, mainBoardType);
	}

	private void prepareHardwares(int cpuType, int mainBoardType) {

		// 根据工厂获取对应的东西
		this.cpu = CpuFactory.createCpu(cpuType);
		this.mainBoard = MainBoardFactory.createMainBorad(mainBoardType);

		this.cpu.calculate();
		this.mainBoard.installCPU();

	}
}

