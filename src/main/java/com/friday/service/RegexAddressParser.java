package com.friday.service;

import com.friday.dto.AddressResponse;
import com.friday.enums.AddressRegexPattern;
import com.friday.errors.MalformedAddressException;

import org.springframework.stereotype.Service;

@Service
public class RegexAddressParser {

  public AddressResponse extractAddress(String address) {
    String trimmedAddress = address.trim().replaceAll(" +", " ");
    AddressResponse addressResponse = null;
    for (AddressRegexPattern pattern : AddressRegexPattern.values()){
      try {
        addressResponse = pattern.parseAddres(trimmedAddress);
        if (addressResponse != null){
          break;
        }
      } catch (IllegalArgumentException e){
      }
    }
    if(addressResponse == null){
      //nothing found
      throw new MalformedAddressException("Unrecognizable address format.");
    }
    return addressResponse;
  }
}
