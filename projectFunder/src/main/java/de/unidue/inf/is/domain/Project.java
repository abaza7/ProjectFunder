package de.unidue.inf.is.domain;

import java.util.ArrayList;

public class Project {

	private String ErstllerEmail;
	private String title;
	private int kennung; 
	private String beschreibung; 
	private String status; 
	private double finanzierungslimit;
	private int kategorie; 
	private int vorgaenger;
	private String ersteller; 
	private double aktulleSpende; 
	private ArrayList<Donation> spende =  new ArrayList<>(); 
	private ArrayList<Comment> Kommentar=  new ArrayList<>(); 
	
	public Project(String title, String beschreibung, double finanzierungslimit,int kategorie, int vorgaenger, String ersteller) {
		super();
		this.title = title;
		this.beschreibung = beschreibung;
		this.finanzierungslimit = finanzierungslimit;
		this.kategorie =kategorie; 
		this.vorgaenger = vorgaenger;
		this.ersteller = ersteller;
	}
	
	//used in UserStore
	public Project(String title, int kennung, String status) {
		super();
		this.title = title;
		this.kennung = kennung;
		this.status = status;
	}


	public Project( int kennung,String title, String beschreibung, String status, double finanzierungslimit,int kat) {
		super();
		this.title = title;
		this.kennung = kennung;
		this.beschreibung = beschreibung;
		this.status = status;
		this.finanzierungslimit = finanzierungslimit;
		this.kategorie =kat;
	}

	
	public Project(String title, int kennung, String beschreibung, String status, double finanzierungslimit,
			int kategorie, int vorgaenger, String ersteller, double aktulleSpende, ArrayList<Donation> spende,
			ArrayList<Comment> kommentar) {
		super();
		this.title = title;
		this.kennung = kennung;
		this.beschreibung = beschreibung;
		this.status = status;
		this.finanzierungslimit = finanzierungslimit;
		this.kategorie = kategorie;
		this.vorgaenger = vorgaenger;
		this.ersteller = ersteller;
		this.aktulleSpende = aktulleSpende;
		this.spende = spende;
		Kommentar = kommentar;
	}


	
	public String getErstllerEmail() {
		return ErstllerEmail;
	}


	public void setErstllerEmail(String erstllerEmail) {
		ErstllerEmail = erstllerEmail;
	}


	public double getAktulleSpende() {
		return aktulleSpende;
	}


	public void setAktulleSpende(double aktulleSpende) {
		this.aktulleSpende = aktulleSpende;
	}


	public ArrayList<Donation> getSpende() {
		return spende;
	}


	public void setSpende(ArrayList<Donation> spende) {
		this.spende = spende;
	}


	public ArrayList<Comment> getKommentar() {
		return Kommentar;
	}


	public void setKommentar(ArrayList<Comment> kommentar) {
		Kommentar = kommentar;
	}


	
	public String getErsteller() {
		return ersteller;
	}



	public void setErsteller(String ersteller) {
		this.ersteller = ersteller;
	}



	
	
	public int getVorgaenger() {
		return vorgaenger;
	}

	public void setVorgaenger(int vorgaenger) {
		this.vorgaenger = vorgaenger;
	}
	
	public int getKategorie() {
		return kategorie;
	}

	public void setKategorie(int kategorie) {
		this.kategorie = kategorie;
	}

	
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getKennung() {
		return kennung;
	}


	public void setKennung(int kennung) {
		this.kennung = kennung;
	}


	public String getBeschreibung() {
		return beschreibung;
	}


	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public double getFinanzierungslimit() {
		return finanzierungslimit;
	}


	public void setFinanzierungslimit(double finanzierungslimit) {
		this.finanzierungslimit = finanzierungslimit;
	}
	
	
	
	
}
