# MNBWebservice

This repository contains a facade class to use accessing a webservice of Hungaryan National Bank (MNB) : http://www.mnb.hu/arfolyamok.asmx.
This service provides actual and historycal MNB issued exchange rates of different currencies compared to hungarian forint (HUF).

Simply instaniate MNBWebserviceFacade class and call it's methods.

Requires Java 8.

For Java 11 use the following dependencies:

      <dependency>
          <groupId>javax.xml.bind</groupId>
          <artifactId>jaxb-api</artifactId>
          <version>2.3.1</version>
      </dependency>
      
      <dependency>
          <groupId>javax.xml.ws</groupId>
          <artifactId>jaxws-api</artifactId>
          <version>2.3.1</version>
      </dependency>

      <dependency>
          <groupId>com.sun.xml.ws</groupId>
          <artifactId>rt</artifactId>
          <version>2.3.1</version>
      </dependency>
