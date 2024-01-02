package me.ynabouzi.miniproject.enums;

public enum Semester {
//	S1, S2, S3, S4, S5
	S1("Semester 1"),
	S2("Semester 2"),
	S3("Semester 3"),
	S4("Semester 4"),
	S5("Semester 5");

	private final String label;

	Semester(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}