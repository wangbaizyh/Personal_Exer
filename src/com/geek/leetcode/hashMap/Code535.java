package com.geek.leetcode.hashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author G.E.E.K.
 * @create 2022-06-29 15:56
 * 535. TinyURL 的加密与解密
 * https://leetcode.cn/problems/encode-and-decode-tinyurl/
 *
 * 思路：模拟 + 哈希表
 * 利用哈希表模拟加密算法
 *
 */
public class Code535 {

}

class Codec {
    // 加密账本 （公钥唯一）
    Map<String, String> origin2Tiny = new HashMap<>();
    // 解密账本 （密钥唯一）
    Map<String, String> tiny2Origin = new HashMap<>();
    String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    String prefix = "http://tinyurl.com/";
    int k = 6;
    Random random = new Random();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        // 是否存在公钥
        while (!origin2Tiny.containsKey(longUrl)) {
            char[] chars = new char[k];
            for (int i = 0; i < k; i++) {
                chars[i] = str.charAt(random.nextInt(str.length()));
            }
            String cur = prefix + String.valueOf(chars);
            // 存在密钥
            if (tiny2Origin.containsKey(cur)) continue;
            tiny2Origin.put(cur, longUrl);
            origin2Tiny.put(longUrl, cur);
        }

        return origin2Tiny.get(longUrl);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return tiny2Origin.get(shortUrl);
    }
}