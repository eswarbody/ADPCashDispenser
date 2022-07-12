package com.example.adp.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.adp.model.Change;
import com.example.adp.util.CoinTypes;

@Service
public class BillChangeService {
	
	//@Autowired
   // EmployeeRepository empRepository;
	
	@Autowired
	static CoinTypes coinTypes;
	
	
	
	//READ
	public Change getChange(Double cents) {
		
       return  getChangeForBill(cents);
		
	}

	
	
	 static Change getChangeForBill(double cents){
		 Change change = new Change();
		 
		 if( cents <= 0.0 ) {
			 change.setError(true);
			 change.setErrorMsg("Invalid Bill Amount");
	         return change;
		 }
		 	double availCash = 0.0;
		// System.out.println(-- CoinTypes.count);
	        for (Map.Entry<Double, Long> entry : coinTypes.getAvailableCoins().entrySet()) {
	             System.out.println("ATM coinType = " + entry.getKey() + ", ATM Coincount = " + entry.getValue());
	             availCash += entry.getKey() * entry.getValue() ; 
	        }
	        double[] coins1 = {.25, .10, .05, .01};
	        double[] coins = {.01, .5, .10, .25};
	        List<Double> result = new ArrayList<Double>();
	        Map<Double,Long> map = new HashMap<>();
	        
	        
	        if(availCash < cents) {
	        	change.setError(true);
	        	change.setErrorMsg("Insufficient Coins");
	        	return change;
	        }
	        for(double coin: coins1){
	            if(cents == 0) break;
	            
	            if((int)(cents / coin) > coinTypes.getAvailableCoins().get(coin)) {
	            	
	                BigDecimal bd = new BigDecimal(cents % coin);
	                bd = bd.setScale(2, RoundingMode.HALF_UP);
	                
	                int a = (int)(cents / coin);
	                long b = coinTypes.getAvailableCoins().get(coin);
	                double c = (a -b ) * coin ;
	                
	                cents = bd.doubleValue() + c;
	                //result.addAll(Collections.nCopies((long)availableCoins.get(coin), coin));
	                map.put(coin,coinTypes.getAvailableCoins().get(coin));
	                coinTypes.getAvailableCoins().put(coin, 0l);
	            }else {
	            	
	            result.addAll(Collections.nCopies((int)(cents / coin), coin));
	            map.put(coin,(long)(cents / coin));
	            BigDecimal bd = new BigDecimal(cents % coin);
	            bd = bd.setScale(2, RoundingMode.HALF_UP);
	           
	            //CoinTypes.availableCoins.put(coin, CoinTypes.availableCoins.get(coin)-(int)(cents / coin));
	            Long changeCointCountValue = coinTypes.getAvailableCoins().get(coin)-(int)(cents / coin);
	            coinTypes.getAvailableCoins().put(coin,changeCointCountValue);
	            cents = bd.doubleValue();
	            //CoinTypes.availableCoins.put(coin, Long.valueOf(-- CoinTypes.count));
	            }
	            
	        }
	        
	        //Map<Integer, Double> map1 = new HashMap<Integer, Double>();  
	        for (Map.Entry<Double, Long> entry : map.entrySet()) {
	             System.out.println("Dispense cointCount:: " + entry.getKey() + ", Dispense CoinValue = " + entry.getValue());
	        }
	        
	        change.setChangeMap(map);
	        change.setError(false);
	        return change;
	    }
}

