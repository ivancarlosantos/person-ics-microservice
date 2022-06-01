package core.ics.person.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import core.ics.person.enums.Gender;
import core.ics.person.enums.PersonStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String personName;
	
	private String cpf;
	
	private String address;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private String token;
	
	private LocalDateTime registerDate;
	
	private LocalDateTime modifyDate;
	
	@Enumerated(EnumType.STRING)
	private PersonStatus status;
	
}
