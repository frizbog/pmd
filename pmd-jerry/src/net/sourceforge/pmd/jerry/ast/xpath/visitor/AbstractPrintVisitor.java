package net.sourceforge.pmd.jerry.ast.xpath.visitor;

public abstract class AbstractPrintVisitor {

	public static final String EOL = System.getProperty("line.separator");

	protected int indentLevel;

	protected final StringBuffer outputBuffer = new StringBuffer();

	protected final StringBuffer lineBuffer = new StringBuffer();

	public String getOutput() {
		flush();
		return outputBuffer.toString();
	}

	protected void print(Object o) {
		print(String.valueOf(o));
	}

	protected void print(String s) {
		lineBuffer.append(s);
	}

	protected void println(Object o) {
		println(String.valueOf(o));
	}

	protected void println(String s) {
		print(s);
		println();
	}

	protected void println() {
		print(EOL);
		flush();
		applyIndent();
	}

	protected void flush() {
		boolean append = false;
		for (int i = 0; i < lineBuffer.length(); i++) {
			if (lineBuffer.charAt(i) != '\t') {
				append = true;
				break;
			}
		}
		if (append) {
			outputBuffer.append(lineBuffer);
		}
		lineBuffer.setLength(0);
	}

	protected void applyIndent() {
		flush();
		for (int i = 0; i < indentLevel; i++) {
			lineBuffer.append('\t');
		}
	}

	protected void incrementIndent() {
		indentLevel++;
		applyIndent();
	}

	protected void decrementIndent() {
		indentLevel--;
		applyIndent();
	}
}
