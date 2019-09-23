package com.javatest;

/**********************************
 * @Name: protocol
 * @Copyright： 个人版权所有
 * @CreateDate： 2019/9/23 13:24
 * @author: HuangFeng
 * @Version： 1.0
 * @Describe:
 *
 **********************************/
public class protocol {

    public static void main(String args[]) {
        byte[] data = {(byte) 0xF4, (byte) 0xFF};
        byte b = (byte) (data[1] << 8 | data[0] & 0xff);//该代码是将拼接为0xfff4
        System.out.println(b + "");
    }
}
