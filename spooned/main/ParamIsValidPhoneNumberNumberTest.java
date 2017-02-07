

package main;


public class ParamIsValidPhoneNumberNumberTest {
    public ParamIsValidPhoneNumberNumberTest(final int number) {
        this.number = number;
    }

    @org.junit.runners.Parameterized.Parameters
    public static java.util.Collection<java.lang.Object[]> params() {
        return java.util.Arrays.asList(new java.lang.Object[]{ 3 }, new java.lang.Object[]{ 5 });
    }

    @org.junit.Test
    public void isValidPhoneNumberNumberTest() {
        Calcul cal = new Calcul();
        cal.setFact(number);
        org.junit.Assert.assertEquals(5, cal.getFact());
    }
}

