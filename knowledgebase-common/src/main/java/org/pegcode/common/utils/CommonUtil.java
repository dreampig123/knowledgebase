package org.pegcode.common.utils;

import cn.hutool.core.util.RandomUtil;

public class CommonUtil {

    public static final String FILE_TYPE= "file";
    public static final String ONLINE_DOC_TYPE= "onlinedoc";

    public static String createFileCode(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(DateUtil.currentTimestamp());
        stringBuilder.append(FILE_TYPE);
        stringBuilder.append(RandomUtil.randomNumbers(6));
        return stringBuilder.toString();
    }

    public static String createOnlineDocCode(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(DateUtil.currentTimestamp());
        stringBuilder.append(ONLINE_DOC_TYPE);
        stringBuilder.append(RandomUtil.randomNumbers(6));
        return stringBuilder.toString();
    }

}
