package ru.edu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "actor")
public class Actor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private Integer age;

	@ManyToMany
	@JoinTable(
			name = "actor_movie",
			joinColumns = @JoinColumn(name = "actor_id"),
			inverseJoinColumns = @JoinColumn(name = "movie_id")
	)
	private List<Movie> movies;

	public Actor() {}

	public Actor(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Actor{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				'}';
	}

}
