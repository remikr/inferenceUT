package main;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestParamCalcul {
	
	private final int number;


	public TestParamCalcul(final int number) {
	    this.number = number;
	}
	
	@Parameters
	public static Collection<Object[]> params() {
	    return Arrays.asList(
	            new Object[] { 3},
	            new Object[] { 5}
	        );
	}
	
	@Test
	public void isValidPhoneNumberNumberTest() {
		Calcul cal = new Calcul();
		cal.setFact(number);
		assertEquals(5,cal.getFact());
	}
	
	@Test
	public void TestDoFunct(){
		Calcul cal = new Calcul();
		cal.setFact(5);
		assertEquals(5,cal.getFact());
	}

}
