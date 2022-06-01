package core.ics.person.utils;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import core.ics.person.model.Address;

@FeignClient(name = "cep-heroku", url = "https://cep-ics-api.herokuapp.com/cep")
public interface AddressRequest {
	
	@GetMapping(path = "/{cep}")
	Address requestCEP(@PathVariable(name = "cep") String cep);
}
