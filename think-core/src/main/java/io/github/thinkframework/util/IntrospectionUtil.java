package io.github.thinkframework.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * 内省工具类
 */
public class IntrospectionUtil {

    public static void setProperty(Object object,String name,Object value){
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            Arrays.stream(beanInfo.getPropertyDescriptors())
                    .filter(propertyDescriptor -> propertyDescriptor.getName().equals(name))
                    .forEach(propertyDescriptor -> {
                        try {
                            propertyDescriptor.getWriteMethod().invoke(object,value);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        } catch (InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }
    }
}
