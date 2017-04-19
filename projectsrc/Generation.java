package projectsrc.projectsrc;

import java.util.Arrays;

public class Generation {
	private int num;
	private double avg;
	private double max;
	private String maxChromoStr;
	
	public Generation(int n) {
		num = n;
		avg = 0;
		max = 0;
		maxChromoStr = "NOT FOUND";
	}

	public Generation(int n, double a, double m) {
		num = n;
		avg = a;
		max = m;
		maxChromoStr = "NOT FOUND";
	}
	
	public void setNum(int num) {
		this.num = num;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public int getNum() {
		return num;
	}

	public double getAvg() {
		return avg;
	}

	public double getMax() {
		return max;
	}

	public void setMaxStr(int[] chromo) {
		maxChromoStr = Arrays.toString(chromo);
	}

	public String getMaxStr() {
		return maxChromoStr;
	}
}
