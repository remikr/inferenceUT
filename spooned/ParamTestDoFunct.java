




class ParamTestDoFunct {
    @org.junit.Test
    public void TestDoFunct() {
        Calcul cal = new Calcul();
        cal.setFact(5);
        org.junit.Assert.assertEquals(5, cal.getFact());
    }
}

