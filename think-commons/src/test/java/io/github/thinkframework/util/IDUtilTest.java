package io.github.thinkframework.util;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertNotNull;

/**
 * ID生成测试
 * @author unascribed
 * @version 1.0.0
 * @since 1.0.0
 */
public class IDUtilTest {

    /**
     * ID生成测试
     */
    @Test
    public void test(){
        Set<Long> set =new HashSet<>();
        Long current = System.currentTimeMillis();
//        while(current.equals(System.currentTimeMillis())){
        for(int i=0;i<4096;i++){
            set.add(IDUtil.getId());
        }
        System.out.println(set.size());
    }
}
