package com.gl.ticketmanagementsystem.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import org.springframework.lang.NonNull;

@Entity // This tells Hibernate to create a table of this class
@Table(name = "Ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// By @GeneratedValue, JPA makes a unique key automatically and applies the key
	// to the field having @Id
	private long id;
	
	@Column
	@NonNull
	private String title;
	
	@Column
	@NonNull
	private String description;
	
	@Column
	@NonNull
	private String content;
	
	@Column
	@NonNull
	private Date date;

	public Ticket() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
}
