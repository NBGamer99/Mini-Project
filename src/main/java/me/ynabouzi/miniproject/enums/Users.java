package me.ynabouzi.miniproject.enums;

public enum Users {
	ADMIN("Admin"),
	PROFESSOR("Prof");

	private final String label;

	Users(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
