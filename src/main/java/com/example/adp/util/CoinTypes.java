package com.example.adp.util;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CoinTypes {

	public static Map<Double, Long> availableCoins;
	@Value("${coinType.25Cents}")
	private long twentyFiveCents;
	
	@Value("${coinType.10Cents}")
	private long tenCents;
	
	@Value("${coinType.5Cents}")
	private long fiveCents;
	
	@Value("${coinType.1Cent}")
	private long oneCent;
	
	
	public static int count = 5;

	@PostConstruct
	public void initiateValues() {
		System.out.print("...............Initiated values............................");
		availableCoins = new HashMap<>();
	  	availableCoins.put(.01, oneCent);
		availableCoins.put(.05, fiveCents);
		availableCoins.put(.1, tenCents);
		availableCoins.put(.25, twentyFiveCents);
	}
 
	
	public static Map<Double, Long> getAvailableCoins(){
		return availableCoins;
	}
}
