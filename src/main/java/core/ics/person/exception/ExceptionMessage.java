package core.ics.person.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExceptionMessage {

	private HttpStatus errorStatus;
	private Date timeError;
	private String message;

	public ExceptionMessage(HttpStatus badRequest, Date timeError, String message) {
		this.errorStatus = badRequest;
		this.timeError = timeError;
		this.message = message;
	}

	public ExceptionMessage(Date timeError, String message) {
		this.timeError = timeError;
		this.message = message;
	}
}
