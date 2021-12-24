package com.demo.load.extend;

import com.alibaba.fastjson.parser.deserializer.SqlDateDeserializer;

/**
 * @author yupeng.wu
 * @since 2021/12/24 15:16
 */
public class ExtendsTest {
    public static void sayHello() {
        System.out.println("hello");
    }

    /**
     * 正常情况下本方法内的所有class只有在第一次运行的时候才会加载。
     * 但是实际上在加载ExtendsTest.class类的时候，会做字节码验证，在验证的时候如果存在类型转换，那么就会导致类的提前加载
     */
    public static void childParentTest() {
        ExtendsHolder extendsHolder = new ExtendsHolder();

        // passParent方法声明的时候接受父类型AbstractDateDeserializer，但实际接受的不是父类型的时候，JVM需要做字节码验证
        // 验证实际类型和父类型是不是可以做类型转换，此时需要加载父类型和子类型
        // 而此时如果没有
         extendsHolder.passParent(new SqlDateDeserializer()); // 不关闭类的校验（-Xverify:none  或者 -noverify）的话，此处会抛出异常NoClassDefFoundError


        // passChild方法声明的时候接受子类型SqlDateDeserializer，实际接受的就是子类型SqlDateDeserializer的时候，不需要做字节码验证
        // 此处不会抛出异常
        // extendsHolder.passChild(new SqlDateDeserializer());
    }

    /**
     * JVM参数：
     * 1.关闭类的校验： -Xverify:none  或者 -noverify
     * 2.追踪类的加载： -verbose:class 或者 -XX:+TraceClassLoading
     */
    public static void main(String[] args) {
        ExtendsTest.sayHello();
    }
}