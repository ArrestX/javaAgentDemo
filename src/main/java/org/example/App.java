package org.example;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.lang.reflect.Field;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void agentmain(String featureString, Instrumentation instrumentation) throws UnmodifiableClassException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        instrumentation.addTransformer(new Transform(), true);
        instrumentation.retransformClasses(Class.forName("java.lang.Runtime"));
    }

    public static void premain(String featureString, Instrumentation instrumentation) throws UnmodifiableClassException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        instrumentation.addTransformer(new Transform(), true);
        instrumentation.retransformClasses(Class.forName("java.lang.Runtime"));
    }

}
