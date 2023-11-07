package com.core.backend.model;

public class AddressModel {

	private String postalCode;
	private String municipality;
	private String interiorNumber;
	private String exteriorNumber;
	private String street;
	private String betweenStreet;
	private String reference;
	
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getMunicipality() {
		return municipality;
	}
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
	public String getInteriorNumber() {
		return interiorNumber;
	}
	public void setInteriorNumber(String interiorNumber) {
		this.interiorNumber = interiorNumber;
	}
	public String getExteriorNumber() {
		return exteriorNumber;
	}
	public void setExteriorNumber(String exteriorNumber) {
		this.exteriorNumber = exteriorNumber;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getBetweenStreet() {
		return betweenStreet;
	}
	public void setBetweenStreet(String betweenStreet) {
		this.betweenStreet = betweenStreet;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	
}
