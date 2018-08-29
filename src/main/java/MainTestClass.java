import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainTestClass {

    public static void start(Class c){

        Method[] methods = c.getDeclaredMethods();
        int counterOfAnnoBS = 0;
        int counterOfAnnoAS = 0;
        Object ob = null;
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

        String before = "";
        String after = "";
        ArrayList<String> tests = new ArrayList<String>(  );

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

        if (counterOfAnnoAS > 1 || counterOfAnnoBS > 1)
            throw new RuntimeException( "Более одной аннотации BeforeSuite или AfterSuite" );


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

        Method Tests = null;
        for (String t: tests) {
            try {

                Tests = TestClass.class.getDeclaredMethod( t );
                Tests.setAccessible( true );
                Tests.invoke( ob );
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

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
