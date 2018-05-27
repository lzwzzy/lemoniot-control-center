package com.lzw.lemoniot.utils;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * MySQL5InnoDBDialectUtf8mb4
 *
 * @author lzw
 * @date 2018/5/23 1:24
 **/
public class MySQL5InnoDBDialectUtf8mb4 extends MySQL5InnoDBDialect {
    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
