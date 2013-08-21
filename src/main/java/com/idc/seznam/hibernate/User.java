package com.idc.seznam.hibernate;

import java.io.Serializable;
import java.net.URL;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idc.seznam.HomeController;

// entity annotace oznaci tridu jako entity bean
@Entity        
@Table(name = "COCHNER_USERS")
public class User implements Serializable{

	private static final long serialVersionUID = 8463886893259949663L;

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@SequenceGenerator(name="user_sequence", sequenceName="SAG_BOOKCASE_SEQ", allocationSize=1)
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_sequence")
	private int uid;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "START_DATE")
	private Date dateStarted;

	@Column(name = "USER_TYPE")
	@Enumerated(EnumType.STRING)
	private UserType type;

	@Transient
	private URL url;



	/**
	 * Constructor
	 */

	public User() {
		uid = 0;
	}

	/**
	 * Generated Setters and Getters
	 */

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(Date dateStarted) {
		this.dateStarted = dateStarted;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Ahoj " + this.getFirstName();
		// return s = "Name: " + this.getFirstName() + " " + this.getLastName()
		// + ", starter: " + this.getDateStarted().getYear() +
		// +this.getDateStarted().getMonth();
	}

	public void updateUser(User user) {
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.dateStarted = user.dateStarted;
		this.type = user.type;
		this.url = user.url;
	}
}
