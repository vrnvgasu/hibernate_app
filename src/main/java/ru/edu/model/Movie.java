package ru.edu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "movie")
public class Movie {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "year_of_production")
	private Integer yearOfProduction;

	@ManyToMany(mappedBy = "movies")
	private List<Actor> actors;

	public Movie() {}

	public Movie(String name, Integer yearOfProduction) {
		this.name = name;
		this.yearOfProduction = yearOfProduction;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYearOfProduction() {
		return yearOfProduction;
	}

	public void setYearOfProduction(Integer yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}

	@Override
	public String toString() {
		return "Movie{" +
				"id=" + id +
				", name='" + name + '\'' +
				", yearOfProduction=" + yearOfProduction +
				'}';
	}

}
