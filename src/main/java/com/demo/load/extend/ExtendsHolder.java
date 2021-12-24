package com.demo.load.extend;

import com.alibaba.fastjson.parser.deserializer.AbstractDateDeserializer;
import com.alibaba.fastjson.parser.deserializer.SqlDateDeserializer;

/**
 * @author yupeng.wu
 * @since 2021/12/24 15:48
 */
public class ExtendsHolder {

    /**
     * 方法调用时接受一个接受一个父类型的对象
     *
     * @param dateDeserializer
     */
    public void passParent(AbstractDateDeserializer dateDeserializer){
        System.out.println(dateDeserializer.getClass().getSimpleName());
    }

    /**
     * 方法调用时接受一个接受一个子类型的对象
     * @param dateDeserializer
     */
    public void passChild(SqlDateDeserializer dateDeserializer){
        System.out.println(dateDeserializer.getClass().getSimpleName());
    }
}
