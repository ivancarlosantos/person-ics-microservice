package core.ics.person.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handlerException(Exception ex, WebRequest request) {

		String errorMessageDescribe = ex.getLocalizedMessage();
		if (errorMessageDescribe == null) {
			errorMessageDescribe = ex.getLocalizedMessage();
		}

		ExceptionMessage error = new ExceptionMessage(HttpStatus.INTERNAL_SERVER_ERROR, new Date(), errorMessageDescribe);

		return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
