package me.ynabouzi.miniproject.enums;

public enum Major {
	GE("Génie Electrique"),
	GI("Génie Informatique"),
	GRT("Génie Réseaux et Télécommunications"),
	IID("Infomatique et Ingénierie des Données"),
	IRIC("Informatique et Réseaux et Communication"),
	GPEE("Génie des Procédés et de l'Environnement");

	private final String label;

	Major(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
