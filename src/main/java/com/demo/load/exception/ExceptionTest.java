package com.demo.load.exception;

import com.alibaba.fastjson.JSONException;

/**
 * @author yupeng.wu
 * @since 2021/12/24 15:27
 */
public class ExceptionTest {

    /**
     * 正常情况下本方法内的所有class只有在第一次运行的时候才会加载。
     * 但是实际上在加载ExceptionTest.class类的时候，会做字节码验证，在验证的时候如果存在异常捕获的场景，那么就会校验该异常是否存在
     */
    public static void testThrow() {
        ExceptionHolder holder = new ExceptionHolder();
        try {
            holder.throwsException();
        } catch (JSONException e) { // 这里捕获一个异常，JVM会校验这个异常是否存在，会导致该异常类的提前加载
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void hello() {
        System.out.println("hello");
    }

    /**
     * JVM参数：
     * 1.关闭类的校验： -Xverify:none  或者 -noverify
     * 2.追踪类的加载： -verbose:class 或者 -XX:+TraceClassLoading
     */
    public static void main(String[] args) {
        // 如果JVM参数加上-Xverify:none  或者 -noverify，那么就不会抛出异常NoClassDefFoundError
        ExceptionTest.hello();
    }
}