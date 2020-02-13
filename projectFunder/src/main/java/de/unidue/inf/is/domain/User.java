package de.unidue.inf.is.domain;

import java.util.ArrayList;

public final class User {

    private String name;
    private String email;
    private String beschreibung;
    private int NoOfOwnedProjects; 
    private int NoOfDonatedProjects; 
    private ArrayList<Project> ownedProjects = new ArrayList<>();
    private ArrayList<Project> DonatedProjects = new ArrayList<>();
    

    
    public User() {
		super();
	}




	public User(String name, String email, int noOfOwnedProjects, int noOfDonatedProjects,
			ArrayList<Project> ownedProjects, ArrayList<Project> donatedProjects) {
		super();
		this.name = name;
		this.email = email;
		NoOfOwnedProjects = noOfOwnedProjects;
		NoOfDonatedProjects = noOfDonatedProjects;
		this.ownedProjects = ownedProjects;
		DonatedProjects = donatedProjects;
	}




	public User(String name, String email, String beschreibung) {
		this.name = name;
		this.email = email;
		this.beschreibung = beschreibung;
	}
	
	
    
    
	public int getNoOfOwnedProjects() {
		return NoOfOwnedProjects;
	}


	public void setNoOfOwnedProjects(int noOfOwnedProjects) {
		NoOfOwnedProjects = noOfOwnedProjects;
	}


	public int getNoOfDonatedProjects() {
		return NoOfDonatedProjects;
	}


	public void setNoOfDonatedProjects(int noOfDonatedProjects) {
		NoOfDonatedProjects = noOfDonatedProjects;
	}


	public ArrayList<Project> getOwnedProjects() {
		return ownedProjects;
	}


	public void setOwnedProjects(ArrayList<Project> ownedProjects) {
		this.ownedProjects = ownedProjects;
	}


	public ArrayList<Project> getDonatedProjects() {
		return DonatedProjects;
	}


	public void setDonatedProjects(ArrayList<Project> donatedProjects) {
		DonatedProjects = donatedProjects;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	} 


    
    
}

    