public class TestClass {

    @BeforeSuite
    public void BeforeTests(){
        System.out.println("К тесту готов!");
    }

    @Test(priority = 1)
    public void test(){
        System.out.println("Тест 1, класса тест проведен!");
    }
    @Test(priority = 2)
    public void test2(){
        System.out.println("Тест 2, класса тест проведен!");
    }
    @Test(priority = 3)
    public void test3(){
        System.out.println("Тест 3, класса тест проведен!");
    }
    @Test(priority = 4)
    public void test4(){
        System.out.println("Тест 4, класса тест проведен!");
    }
    @Test(priority = 5)
    public void test5(){
        System.out.println("Тест 5, класса тест проведен!");
    }
    @Test(priority = 6)
    public void test6(){
        System.out.println("Тест 6, класса тест проведен!");
    }
    @AfterSuite
    public void AfterTest(){
        System.out.println("Тесты завершены!");
    }
}
