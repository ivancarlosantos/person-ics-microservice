package core.ics.person.utils;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import core.ics.person.model.GenerateToken;

@FeignClient(name = "generate-token", url = "https://generate-token-api.herokuapp.com")
public interface Token {

	@GetMapping(path = "/generate/token")
	GenerateToken generateToken();
}
