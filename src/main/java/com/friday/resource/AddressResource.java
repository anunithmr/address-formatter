package com.friday.resource;

import javax.validation.Valid;
import javax.validation.Validator;

import com.friday.dto.AddressRequest;
import com.friday.dto.AddressResponse;
import com.friday.service.RegexAddressParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "address")
public class AddressResource {

  private final RegexAddressParser regexAddressParser;

  @Autowired
  AddressResource(RegexAddressParser regexAddressParser){
    this.regexAddressParser = regexAddressParser;
  }

  @RequestMapping(method = RequestMethod.POST)
  public AddressResponse getAddressLine(@RequestBody @Valid AddressRequest addressRequest){
    return regexAddressParser.extractAddress(addressRequest.getAddress());
  }
}
