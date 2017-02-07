

package main;


@org.junit.runner.RunWith(value = org.junit.runners.Parameterized.class)
public class ParamTestDoFunct {
    int a;

    public ParamTestDoFunct(final int a) {
        this.a = a;
    }

    @org.junit.runners.Parameterized.Parameters
    public static java.util.Collection<java.lang.Object[]> params() {
        return java.util.Arrays.asList(new java.lang.Object[]{ 5 }, new java.lang.Object[]{ 5 });
    }

    @org.junit.Test
    public void TestDoFunct() {
        Calcul cal = new Calcul();
        cal.setFact(a);
        org.junit.Assert.assertEquals(a, cal.getFact());
    }
}

