package core.ics.person.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GenerateToken implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String token;
	
	private OffsetDateTime createdToken;
	
}
