package maths;

public class BrokenMaths extends BetterMaths {
	int bv;
	
	public BrokenMaths(int brokenValue) {
		this.bv = brokenValue;
	}
	
	int add(int first, int second) {
		return first + second;
	}
}
