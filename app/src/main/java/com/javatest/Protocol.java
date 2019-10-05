package com.javatest;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**********************************
 * @Name: Protocol
 * @Copyright： 个人版权所有
 * @CreateDate： 2019/9/23 13:24
 * @author: HuangFeng
 * @Version： 1.0
 * @Describe:
 *
 **********************************/
public class Protocol {

    public static void main(String args[]) {
//        byte[] data = {(byte) 0xF4, (byte) 0xFF};
//        byte b = (byte) (data[1] << 8 | data[0] & 0xff);//该代码是将拼接为0xfff4
//        System.out.println(b + "");


        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer = byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.put((byte) 0xFF);
        byteBuffer.put((byte) 0xFE);
        byteBuffer.putInt(2);
        //心电数据类型为01
        byteBuffer.put((byte) 0x01);


        byte[] b = byteBuffer.array();
        for (byte b1 : b ){
            System.out.print(b1 + "");
        }
        System.out.print( "\n");
        b[2] = (byte) 0xFF;
        for (byte b1 : b ){
            System.out.print(b1 + "");
        }

    }






}
