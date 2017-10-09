package cn.niriqiang.blog.util;

import org.springframework.util.DigestUtils;

/**
 * Created by fengyuwusong on 2017/10/9 16:04.
 */
public class Md5Util {
    //md5盐值字符串，用于混淆md5
    private static final String salt = "12321ghvbhashd$!@#$!@#vkasghv122kfvopqgsduias!@$%*(@&*ldnbjklas";

    //    生成MD5
    public static String getMD5(String string) {
        String base = string + "/" + salt;
//        生成md5
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }
}
