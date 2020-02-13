package de.unidue.inf.is.domain;

public class Donation {
	private String owner;
	private String email;
	private int project; 
	private double amount; 
    private String privacy;
	
	
	
	public Donation(String owner, String email, int project, double amount, String privacy) {
		super();
		this.owner = owner;
		this.email = email;
		this.project = project;
		this.amount = amount;
		this.privacy = privacy;
	}
	
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPrivacy() {
		return privacy;
	}
	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getProject() {
		return project;
	}
	public void setProject(int project) {
		this.project = project;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	} 
	
	
	
	
}
