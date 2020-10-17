package com.learn.springbootdemo.util;

/**
 * @Description:
 * @Author: Scuman
 * @Time: 2020/10/17 3:12 PM
 */
public class ObjectUtils {
    public static final String LONG = "Long";
    public static final String INTEGER = "Integer";
    public static final String DOUBLE = "Double";
    public static final String STRING = "String";
    public static String checkObjectType(Object obj) {
        try {
            double obj1 = (double) obj;
            return DOUBLE;
        } catch (Exception e1) {
            try {
                int obj1 = (int) obj;
                return INTEGER;
            } catch (Exception e2) {
                try {
                    long obj1 = (long) obj;
                    return LONG;
                } catch (Exception e3) {
                    return STRING;
                }
            }
        }
    }
}
