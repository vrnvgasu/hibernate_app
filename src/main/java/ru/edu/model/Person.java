package ru.edu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity // Аннотация связываем класс с БД
@Table(name = "person")
public class  Person {

	@Id // для первичного ключа
	@Column(name = "id")
	// Автоинкремент на стороне postgres. Не трогаем ее поведение в коде
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// указываем на последовательность. Нужно будет указать и ссылку на саму последовательность
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "name_из_SequenceGenerator")
//	allocationSize - на сколько увеличиваем сиквенс
//	@SequenceGenerator(name = "любое_название", sequenceName = "название_сиквенса_в_бд", allocationSize = 1)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private Integer age;

	// ссылаемся на свойство owner из сущности Item
	// CascadeType.PERSIST - сохраняет связанные сущности
	// будет работать только для стандартного метода JPA - persist (для save - нет)
//	@OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
	@OneToMany(mappedBy = "owner")
	// правило каскадирования для метода save (этот метод из Hibernate, а не JPA)
	@Cascade(CascadeType.SAVE_UPDATE)
	// обновляет relation при обновлении связанной сущности
//	@Cascade(CascadeType.REFRESH)
	private List<Item> items;

	@OneToOne(mappedBy = "person")
	@Cascade(CascadeType.SAVE_UPDATE)
	private Passport passport;

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
		passport.setPerson(this);
	}

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

	public void addItem(Item item) {
		if (this.items == null) {
			this.items = new ArrayList<>();
		}

		this.items.add(item);
		item.setOwner(this);
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
