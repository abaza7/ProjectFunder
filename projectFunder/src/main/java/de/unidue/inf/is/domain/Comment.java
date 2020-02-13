package de.unidue.inf.is.domain;

import java.sql.Timestamp;

public class Comment {

	private String email;
	private String owner;
	private String text;
	private Timestamp date;
	private String privacy;
	private int project;
	private int id;

	public Comment(String owner, String text, Timestamp date, String privacy, int project, int id, String email) {
		super();
		this.owner = owner;
		this.text = text;
		this.date = date;
		this.privacy = privacy;
		this.project = project;
		this.id = id;
		this.email = email;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getProject() {
		return project;
	}

	public void setProject(int project) {
		this.project = project;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}
}
