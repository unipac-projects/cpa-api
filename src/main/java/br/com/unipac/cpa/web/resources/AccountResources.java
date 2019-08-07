package br.com.unipac.cpa.web.resources;

import br.com.unipac.cpa.model.domain.User;
import br.com.unipac.cpa.web.dto.request.AccountRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codahale.metrics.annotation.Timed;

import br.com.unipac.cpa.model.service.AccountService;

@RestController
@RequestMapping("/v1/register")
public class AccountResources {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountService accountService;

    @Autowired
    private ConversionService accountRequestConverter;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Timed
	public ResponseEntity<Boolean> register(@RequestBody AccountRequest accountRequest) {
        logger.info("Account Service: " + accountRequest);
        User user = accountRequestConverter.convert(accountRequest, User.class);
        boolean registered = accountService.register(user);
    	return new ResponseEntity<>(true, HttpStatus.CREATED);
	}
}