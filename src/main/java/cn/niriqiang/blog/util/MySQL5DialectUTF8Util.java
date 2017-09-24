package cn.niriqiang.blog.util;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * Created by fengyuwusong on 2017/9/25 0:57.
 */
public class MySQL5DialectUTF8Util extends MySQL5InnoDBDialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
