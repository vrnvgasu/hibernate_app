package ru.edu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity // Аннотация связываем класс с БД
@Table(name = "person")
public class Person {

	@Id // для первичного ключа
	@Column(name = "id")
	// Автоинкремент на стороне postgres. Не трогаем ее поведение в коде
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// указываем на последовательность. Нужно будет указать и ссылку на саму последовательность
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "name_из_SequenceGenerator")
//	allocationSize - на сколько увеличиваем сиквенс
//	@SequenceGenerator(name = "любое_название", sequenceName = "название_сиквенса_в_бд", allocationSize = 1)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private Integer age;

	public Person() {}

	public Person(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
		return "Person{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				'}';
	}

}
