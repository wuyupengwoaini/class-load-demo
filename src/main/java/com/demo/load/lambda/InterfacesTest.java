package com.demo.load.lambda;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author yupeng.wu
 * @since 2021/12/24 16:59
 */
public class InterfacesTest {
    public static void sayHello() {
        System.out.println("hello");
    }

    public static void testInterfaces(){
        InterfacesHolder holder = new InterfacesHolder();

        // 不会抛出异常
        // 原因：lambda表达式在编译期只会生成方法名类似于lambda$0的静态私有方法，不会生成对应接口实现类的class,对应class是在运行期生成
        //      所以在校验本类的字节码的时候是不需要校验类型的
        // 关于lambda表达式的实现原理参考：https://www.cnblogs.com/WJ5888/p/4667086.html
        //holder.invokeInterfaces((serializer, object, fieldName, fieldType, features) -> {
            // do nothing
        //});

        // 会抛出异常
        // 原因：匿名内部类在编译期就生成了对应接口的实现类，所以在校验本类字节码的时候会校验类型
        holder.invokeInterfaces(new ObjectSerializer() {
            @Override
            public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
                // do nothing
            }
        });
    }

    public static void main(String[] args) {
        InterfacesTest.sayHello();
    }

}
