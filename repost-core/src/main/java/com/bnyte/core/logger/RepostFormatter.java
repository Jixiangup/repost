package com.bnyte.core.logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * @auther bnyte
 * @date 2021-09-06 17:17
 * @email bnytezz@163.com
 */
public class RepostFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        // 创建储存日志对象
        StringBuilder loggerBuilder = new StringBuilder();
        // 1.打印时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = new Date();
        String dataStr = dateFormat.format(date);

        // 添加日期打印
        loggerBuilder.append(dataStr);
        loggerBuilder.append(" - ");

        // 凭借日志级别
        loggerBuilder.append(record.getLevel()).append(" - ");

        // 拼接方法名
        loggerBuilder.append(record.getSourceMethodName()).append(" - ");

        // 日志内容
        loggerBuilder.append(record.getMessage());



//        loggerBuilder.
        return loggerBuilder.toString();
    }

    @Override
    public String getHead(Handler h) {
        // 日志头部信息
        return super.getHead(h);
    }

    @Override
    public String getTail(Handler h) {
        // 日志尾部信息
        return super.getTail(h);
    }
}
