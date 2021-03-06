package core.ics.person.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import core.ics.person.enums.Gender;
import core.ics.person.enums.PersonStatus;
import core.ics.person.model.Address;
import core.ics.person.model.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String personName;
	
	private String cpf;
	
	private Address address;
	
	private Gender gender;
	
	private String token;
	
	@JsonIgnore
	private LocalDateTime modifyDate;
	
	private PersonStatus status;
	
	public PersonDTO(Person p) {
		this.id = p.getId();
		this.personName = p.getPersonName();
		this.cpf = p.getCpf();
		this.gender = p.getGender();
		this.token = p.getToken();
		this.status = p.getStatus();
	}
	
}
