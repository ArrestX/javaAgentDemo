package org.example;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

/**
 * @program: javaAgentDemo
 * @description: classFileTransform
 * @author: potats0
 * @create: 2021-10-14 15:29
 **/
public class Transform implements ClassFileTransformer {

    @Override
    public byte[] transform(final ClassLoader loader, final String className, final Class<?> classBeingRedefined, final ProtectionDomain protectionDomain, final byte[] classfileBuffer) {

        if ("java/lang/Runtime".equals(className)) {
            try {
                final ClassPool classPool = ClassPool.getDefault();
                final CtClass clazz = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
                CtMethod execMethod = clazz.getDeclaredMethod("exec", new CtClass[]{classPool.get("java.lang.String")});
                execMethod.insertBefore("{" +
                        "System.out.println($1);}");
                byte[] byteCode = clazz.toBytecode();
                clazz.detach();
                return byteCode;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
