package io.github.thinkframework.util;

import io.github.thinkframework.snowflake.IdWorker;

import java.util.Random;

/**
 * ID生成器
 * @author unascribed
 * @version 1.0.0
 * @since 1.0.0
 */
public class IDUtil {

    private static final IdWorker idWorker = new IdWorker();
    /**
     * id
     * @return ID
     */
    public static Long getId(){
        return idWorker.nextId();
    }
}
