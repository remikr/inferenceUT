

package main;


public class ParamTestDoFunct {
    public ParamTestDoFunct(final int number) {
        this.number = number;
    }

    @main.Parameters
    public static main.Collection<java.lang.Object[]> params() {
        return Arrays.asList(new java.lang.Object[]{ 3 }, new java.lang.Object[]{ 5 });
    }

    @org.junit.Test
    public void TestDoFunct() {
        Calcul cal = new Calcul();
        cal.setFact(5);
        org.junit.Assert.assertEquals(5, cal.getFact());
    }
}

