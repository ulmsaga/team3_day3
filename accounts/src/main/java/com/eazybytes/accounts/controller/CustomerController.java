package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.dto.CardsDto;
import com.eazybytes.accounts.service.client.CardsFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.service.IAccountsService;

import jakarta.validation.constraints.Pattern;

@RestController

@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {

    private final IAccountsService iAccountsService;

    @Autowired
    public CustomerController(IAccountsService iAccountsService) {
        this.iAccountsService = iAccountsService;
    }

    @GetMapping("/fetchCustomerDetailInfo")
    public ResponseEntity<CardsDto> fetchCustomerDetailInfo(@RequestParam
                                                               @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                               String mobileNumber) {
        CardsDto cardsDto = iAccountsService.fetchCardDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

}
