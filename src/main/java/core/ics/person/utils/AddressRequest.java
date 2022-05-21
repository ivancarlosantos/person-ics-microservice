package core.ics.person.utils;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import core.ics.person.model.Address;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface AddressRequest {
	
	@GetMapping(path = "/{cep}/json/")
	Address requestCEP(@PathVariable(name = "cep") String cep);
}
