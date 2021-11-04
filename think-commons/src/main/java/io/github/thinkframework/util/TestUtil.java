package io.github.thinkframework.util;

/**
 * 单元测试工具类
 * @author unascribed
 * @version 1.0.0
 * @since 1.0.0
 */
public class TestUtil {

    public static byte[] convertObjectToJsonBytes(Object obj){
        return JsonUtil.convertObjectToJsonBytes(obj);
    }

    public static String convertObjectToJsonString(Object obj){
        return JsonUtil.convertObjectToJsonString(obj);
    }

    public static <T> T convertJosonStringToObject(String content, Class<T> toValueType){
        return JsonUtil.convertJsonStringToObject(content,toValueType);
    }


//    public static <T> T convertJosonStringToObject(String content, JavaType valueType){
//        return JsonUtil.readValue(content,valueType);
//    }
}

