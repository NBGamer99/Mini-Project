package me.ynabouzi.miniproject.enums;

public enum Major {
	IID("Infomatique et Ingénierie des Données"),
	GI("Génie Informatique"),
	GPEE("Génie des Procédés et de l'Environnement"),
	IRIC("Informatique et Réseaux Industriels et Communication"),
	GRT("Génie Réseaux et Télécommunications");

	private final String label;

	Major(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
