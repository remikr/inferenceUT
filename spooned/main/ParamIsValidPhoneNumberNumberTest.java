

package main;


@org.junit.runner.RunWith(value = org.junit.runners.Parameterized.class)
public class ParamIsValidPhoneNumberNumberTest {
    int a;

    public ParamIsValidPhoneNumberNumberTest(final int a) {
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

