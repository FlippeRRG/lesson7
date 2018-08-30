import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class MainTestClass {

    private static Object ob;
    private static  Method[] methods;
    private static  ArrayList<String> tests = new ArrayList<String>(  );
    private static int counterOfAnnoBS = 0;
    private static int counterOfAnnoAS = 0;
    private static String before = "";
    private static String after = "";

    public static void start(Class c){
        ob = null;
        methods = c.getDeclaredMethods();

        createInstance( c );
        findTestMethods();

        if (counterOfAnnoAS > 1 || counterOfAnnoBS > 1)
            throw new RuntimeException( "Более одной аннотации BeforeSuite или AfterSuite" );

        startBeforeSuite( c );
        startTests( c );
        startAfterSuite( c );
    }

    private static void createInstance(Class c){
        try {
            Constructor constructor = c.getConstructor(  );
            try {
                ob = constructor.newInstance(  );
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private static void findTestMethods (){

        for (Method o : methods ) {
            if (o.isAnnotationPresent( BeforeSuite.class )) {
                counterOfAnnoBS++;
                before = o.getName();
            }
            if (o.isAnnotationPresent( AfterSuite.class )) {
                counterOfAnnoAS++;
                after = o.getName();
            }
            if (o.isAnnotationPresent( Test.class )) {
                tests.add( o.getName() );
            }
        }
    }
    private static void startBeforeSuite(Class c){
        Method BeforeSuite = null;
        try {
            BeforeSuite = c.getMethod( before );
            BeforeSuite.invoke( ob );
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    private static void startTests(Class c){
        Method Tests = null;
        try {
            for (int i = 0; i < 10; i++) {
                for (String t : tests) {
                    Tests = c.getMethod( t );
                    Test test = Tests.getAnnotation(Test.class);
                    if(test.priority()==i) Tests.invoke( ob );
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    private static void startAfterSuite(Class c){
        Method AfterSuite = null;
        try {
            AfterSuite = c.getMethod( after );
            AfterSuite.invoke( ob );
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
