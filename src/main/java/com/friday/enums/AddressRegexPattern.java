package com.friday.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.friday.dto.AddressResponse;
import com.friday.service.AddressParser;

public enum AddressRegexPattern implements AddressParser{
  REGEX_PATTERN_1("^(.*)(No|NO )(.*)$") {
    public AddressResponse parseAddres(String address) {
      Matcher matcher = this.match(address);
      return new AddressResponse(matcher.group(2) + matcher.group(3),
          matcher.group(1).trim());
    }
  },
  REGEX_PATTERN_2("^([A-Za-z \\p{L}]+)(,){0,1}([\\d ]+)$") {
    public AddressResponse parseAddres(String address) {
      Matcher matcher = this.match(address);
      return new AddressResponse(matcher.group(3).trim(), matcher.group(1).trim());
    }
  },
  REGEX_PATTERN_3("^([A-Za-z \\p{L}]+)([\\d ]+)([A-Za-z]+)$") {
    public AddressResponse parseAddres(String address) {
      Matcher matcher = this.match(address);
      return new AddressResponse(matcher.group(2) + matcher.group(3).trim(),
          matcher.group(1).trim());
    }
  },
  REGEX_PATTERN_4("^(\\d+)(,){0,1}([A-Za-z ]+)$") {
    public AddressResponse parseAddres(String address) {
      Matcher matcher = this.match(address);
      return new AddressResponse(matcher.group(1).trim(), matcher.group(3).trim());
    }
  };

  public Pattern pattern;
  private String patternRegex;

  AddressRegexPattern(String patternString){
    this.patternRegex = patternString;
    this.pattern = Pattern.compile(patternString);
  }

  public Matcher match(String address){
    Matcher matcher = this.pattern.matcher(address);
    if(matcher.find()){
      return matcher;
    }
    throw new IllegalArgumentException("Unparsable string for pattern "+ this.patternRegex);
  }
}
