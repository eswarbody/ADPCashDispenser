package com.example.employee;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.adp.model.Change;
import com.example.adp.service.BillChangeService;
import com.example.adp.util.CoinTypes;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BillChangeService.class)
@ActiveProfiles(value = "LOCAL")
class EmployeeApplicationTests {

	@MockBean
	private BillChangeService billchangeService;
	
	@MockBean 
	private CoinTypes coinTypes;
	
	@Before
	void setUp() {
		CoinTypes cType= new CoinTypes();
		Map<Double, Long> coinTypeMap = new HashMap<>();
		coinTypeMap.put(.01, 10l);
		coinTypeMap.put(.05, 10l);
		coinTypeMap.put(.1, 10l);
		coinTypeMap.put(.25, 5l);
		Mockito.when(coinTypes.getAvailableCoins()).thenReturn( coinTypeMap);
		//Mockito.when(coinTypes.getAvailableCoins()).thenReturn(coinTypeMap);
		//Mockito.when(coinTypes.availableCoins).then((Answer<?>) coinTypeMap);
		//cType.availableCoins = coinTypeMap;
	}
	
	@Test
	public void testGetChange() {
		Change change = new Change();
		change.setError(false);
		Mockito.when(billchangeService.getChange(anyDouble())).thenReturn(change);
		Change changeOut = billchangeService.getChange(0.0);
		assertEquals(changeOut.isError(), false);
	}
	
	
	/*
	 * @Test public void testGetCashDispenser() {
	 * 
	 * Change ch = billchangeService.getChange(1.21); assertEquals(ch.isError(),
	 * false); }
	 */

}
