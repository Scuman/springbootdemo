package com.learn.springbootdemo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.learn.springbootdemo.dataobject.JsonInfo;
import com.learn.springbootdemo.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: Scuman
 * @Time: 2020/10/17 11:24 AM
 */
public class JsonToClassService {
    private static final String CLASS = "class";
    private static final String CLASS_NAME = "Class";
    private static final String CLASS_FRONT = "{";
    private static final String CLASS_END = "}";
    private static final String ARRAY_FRONT = "[";
    private static final String ARRAY_END = "]";
    private static final String GENERIC_FRONT = "<";
    private static final String GENERIC_END = ">";
    private static final String NEXT_LINE = "\n";
    private static final String SPACE = " ";
    private static final String TAB = "    ";
    private static final String LINE_END = ";";
    private static final String PRIVATE = "private";
    private static final String EQUAL = "=";
    private static final String LIST = "List";


    public String parseJsonToClass(JsonInfo jsonInfo) {
        JSONObject jsonObject = JSONObject.parseObject(jsonInfo.getJsonString());
        AtomicInteger classNum = new AtomicInteger(1);
        List<StringBuilder> classList = new ArrayList<>();
        processJsonObject(jsonObject, classNum, classList);
        StringBuilder result = new StringBuilder();
        classList.forEach(sb -> result.append(sb).append(NEXT_LINE).append(NEXT_LINE));
        return result.toString();
    }

    private String processJsonObject(JSONObject jsonObject, AtomicInteger classNum, List<StringBuilder> classList) {
        StringBuilder result = new StringBuilder();
        String className = CLASS_NAME + classNum.getAndIncrement();
        result.append(CLASS).append(SPACE).append(className).append(SPACE).append(CLASS_FRONT).append(NEXT_LINE);
        if (!jsonObject.isEmpty()) {
            jsonObject.forEach((key, obj) -> processType(result, key, obj, classNum, classList));
        }
        result.append(CLASS_END);
        classList.add(result);
        return className;
    }

    private String processJsonArray(JSONArray jsonArray, AtomicInteger classNum, List<StringBuilder> classList) {
        String value = (String) jsonArray.get(0);
        // todo 可能要考虑List嵌套List的情况
        if (value.startsWith(CLASS_FRONT)) {
            return processJsonObject(JSONObject.parseObject(value), classNum, classList);
        } else {
            return ObjectUtils.checkObjectType(value);
        }
    }

    private void processType(StringBuilder result, String key, Object obj, AtomicInteger classNum, List<StringBuilder> classList) {
        String value = (String) obj;
        if (value.startsWith(CLASS_FRONT)) {
            String className = processJsonObject(JSONObject.parseObject(value), classNum, classList);
            result.append(TAB).append(PRIVATE).append(SPACE).append(className).append(SPACE).append(key).append(LINE_END).append(NEXT_LINE);
        } else if (value.startsWith(ARRAY_FRONT)) {
            String className = processJsonArray(JSONObject.parseArray(value), classNum, classList);
            result.append(TAB).append(PRIVATE).append(SPACE).append(LIST).append(GENERIC_FRONT).append(className).append(GENERIC_END).append(SPACE).append(key).append(LINE_END).append(NEXT_LINE);
        } else {
            result.append(TAB).append(PRIVATE).append(SPACE).append(ObjectUtils.checkObjectType(value)).append(SPACE).append(key).append(LINE_END).append(NEXT_LINE);
        }
    }
}
