package core.ics.person.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import core.ics.person.enums.Gender;
import core.ics.person.enums.PersonStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private Gender gender;
	
	private String accessKey;
	
	private LocalDateTime registerDate;
	
	private LocalDateTime modifyDate;
	
	private PersonStatus status;
}
