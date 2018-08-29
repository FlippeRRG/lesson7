public class TestClass {

    @BeforeSuite
    public void BeforeTests(){
        System.out.println("К тесту готов!");
    }

    @Test
    public void test(){
        System.out.println("Тест 1, класса тест проведен!");
    }
    @Test
    public void test2(){
        System.out.println("Тест 2, класса тест проведен!");
    }
    @Test
    public void test3(){
        System.out.println("Тест 3, класса тест проведен!");
    }
    @AfterSuite
    public void AfterTest(){
        System.out.println("Тесты завершены!");
    }
}
