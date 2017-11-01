package com.friday.service;

import com.friday.dto.AddressResponse;
import com.friday.errors.MalformedAddressException;

import org.junit.Assert;
import org.junit.Test;

public class RegexAddressParserTest {

  @Test
  public void testSimplePattern(){
    String address = "Street 1";
    RegexAddressParser addressParser = new RegexAddressParser();
    AddressResponse response = addressParser.extractAddress(address);

    Assert.assertTrue("Street".equals(response.getStreetName()));
    Assert.assertEquals("1", response.getStreetNumber());

    address = "Street Name 100";
    addressParser = new RegexAddressParser();
    response = addressParser.extractAddress(address);

    Assert.assertTrue("Street Name".equals(response.getStreetName()));
    Assert.assertEquals("100", response.getStreetNumber());
  }

  @Test
  public void testSimplePatternWithAlphaNumericStreetNumber(){
    String address = "Blaufeldweg 123B";
    RegexAddressParser addressParser = new RegexAddressParser();
    AddressResponse response = addressParser.extractAddress(address);

    Assert.assertTrue("Blaufeldweg".equals(response.getStreetName()));
    Assert.assertEquals("123B", response.getStreetNumber());

    address = "Auf der Vogelwiese 23 b";
    addressParser = new RegexAddressParser();
    response = addressParser.extractAddress(address);

    Assert.assertTrue("Auf der Vogelwiese".equals(response.getStreetName()));
    Assert.assertEquals("23 b", response.getStreetNumber());
  }

  @Test
  public void testPatternWithSpecialChar(){
    String address = "Am Bächle 23";
    RegexAddressParser addressParser = new RegexAddressParser();
    AddressResponse response = addressParser.extractAddress(address);

    Assert.assertTrue("Am Bächle".equals(response.getStreetName()));
    Assert.assertEquals("23", response.getStreetNumber());

    address = "Am Bächle 23b ";
    addressParser = new RegexAddressParser();
    response = addressParser.extractAddress(address);

    Assert.assertTrue("Am Bächle".equals(response.getStreetName()));
    Assert.assertEquals("23b", response.getStreetNumber());
  }

  @Test
  public void testPatternWithNumberCommaName(){
    String address = "4,rue de la revolution";
    RegexAddressParser addressParser = new RegexAddressParser();
    AddressResponse response = addressParser.extractAddress(address);

    Assert.assertTrue("rue de la revolution".equals(response.getStreetName()));
    Assert.assertEquals("4", response.getStreetNumber());
  }

  @Test
  public void testPatternWithNumberThenName(){
    String address = "200 Broadway Av";
    RegexAddressParser addressParser = new RegexAddressParser();
    AddressResponse response = addressParser.extractAddress(address);

    Assert.assertTrue("Broadway Av".equals(response.getStreetName()));
    Assert.assertEquals("200", response.getStreetNumber());
  }

  @Test
  public void testPatternWithNameCommaNumber(){
    String address = "Calle Aduana, 29";
    RegexAddressParser addressParser = new RegexAddressParser();
    AddressResponse response = addressParser.extractAddress(address);

    Assert.assertTrue("Calle Aduana".equals(response.getStreetName()));
    Assert.assertEquals("29", response.getStreetNumber());
  }

  @Test
  public void testPatternWithNumberAsNO(){
    String address = "Calle 39 No 1540";
    RegexAddressParser addressParser = new RegexAddressParser();
    AddressResponse response = addressParser.extractAddress(address);

    Assert.assertTrue("Calle 39".equals(response.getStreetName()));
    Assert.assertEquals("No 1540", response.getStreetNumber());
  }

  @Test
  public void testIllegalAddressFormat(){
    String address = "Calle";
    RegexAddressParser addressParser = new RegexAddressParser();
    try {
      AddressResponse response = addressParser.extractAddress(address);
    } catch (MalformedAddressException e){
      Assert.assertEquals("Unrecognizable address format.", e.getMessage());
    }
  }
}
