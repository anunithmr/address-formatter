package com.friday.service;

import java.util.IllegalFormatException;

import com.friday.dto.AddressResponse;
import com.friday.enums.RegexPattern;

import org.springframework.stereotype.Service;

@Service
public class RegexAddressParser {

  public AddressResponse extractAddress(String address) {
    String trimmedAddress = address.trim().replaceAll(" +", " ");
    AddressResponse addressResponse = null;
    for (RegexPattern pattern : RegexPattern.values()){
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
      throw new IllegalArgumentException("Nothing found");
    }
    return addressResponse;
  }
}
