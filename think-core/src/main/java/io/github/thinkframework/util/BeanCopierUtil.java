package io.github.thinkframework.util;

import org.springframework.cglib.beans.BeanCopier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 性能远高于BeanUtils
 * @author unascribed
 * @version 1.0.0
 * @since 1.0.0
 */
public class BeanCopierUtil {

    /**
     * 享元模式
     */
    private static final Map<String,BeanCopier> FLYWEIGHT = new ConcurrentHashMap();

    /**
     *
     * @param source
     * @param target
     */
    public static void copy(Object source, Object target){
         FLYWEIGHT.computeIfAbsent(String.format("%s_%s",source.getClass(),target.getClass()),
                (k) ->  BeanCopier.create(source.getClass(), target.getClass(), false))
                .copy(source, target, null);
    }
}
