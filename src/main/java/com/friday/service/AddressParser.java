package com.friday.service;

import com.friday.dto.AddressResponse;

public interface AddressParser {
  AddressResponse parseAddres(String address);
}
