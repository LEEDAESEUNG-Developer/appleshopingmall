package com.appleshopingmall.util;

import java.sql.Date;

public class DateConverter {

    private static DateConverter instance = new DateConverter();

    private DateConverter(){}

    public static DateConverter getInstance() {
        return instance;
    }

    public static Date utilDateToSql(java.util.Date date) {
        System.out.println("date = " + date.toString());
        return new Date(date.getTime());
    }
}
