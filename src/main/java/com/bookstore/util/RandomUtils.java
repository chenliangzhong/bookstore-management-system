package com.bookstore.util;

import java.util.Random;

public class RandomUtils {
    public static int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    public static Random random = new Random();

    /**
     * 生成随机数字
     *
     * @param length 随机数长度
     * @return
     */
    public static String randomNumber(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(10);
            builder.append(nums[index]);
        }
        return builder.toString();
    }
}
