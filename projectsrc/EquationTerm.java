package projectsrc.projectsrc;

public class EquationTerm {
	
	private final char var;
	private final int constant;
	
	public EquationTerm(char v, int c) {
		var = v;
		constant = c;
	}
	
	public int getConstant() { return constant; }

}
