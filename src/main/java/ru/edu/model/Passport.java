package ru.edu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "passport")
public class Passport {
		// Serializable - если @Id не числовое поле
		//implements Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// Если у нас первичный ключ не числовой,
	// то реализовывать implements Serializable
//	@Id
	@OneToOne
	@JoinColumn(name = "person_id", referencedColumnName = "id")
	private Person person;

	@Column(name = "passport_number")
	private Integer passportNumber;

	public Passport() {
	}

	public Passport(Integer passportNumber) {
		this.passportNumber = passportNumber;
	}

	public Passport(Person person, Integer passportNumber) {
		this.person = person;
		this.passportNumber = passportNumber;
	}

	public Person getPerson() {
		return person;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Integer getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(Integer passportNumber) {
		this.passportNumber = passportNumber;
	}

	@Override
	public String toString() {
		return "Passport{" +
				"person=" + person +
				", passportNumber=" + passportNumber +
				'}';
	}

}
