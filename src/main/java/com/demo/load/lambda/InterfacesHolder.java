package com.demo.load.lambda;

import com.alibaba.fastjson.serializer.ObjectSerializer;

/**
 * @author yupeng.wu
 * @since 2021/12/24 16:58
 */
public class InterfacesHolder {

    public void invokeInterfaces(ObjectSerializer serializer){
        System.out.println(serializer.getClass().getSimpleName());
    }
}
