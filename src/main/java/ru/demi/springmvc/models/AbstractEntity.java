package ru.demi.springmvc.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class AbstractEntity {
	@Id
	@GeneratedValue
	private Integer id;
	@Version
	private Integer version;

	public AbstractEntity(Integer id) {
		this.id = id;
	}

	public boolean isNew() {
		return id == null;
	}
}
