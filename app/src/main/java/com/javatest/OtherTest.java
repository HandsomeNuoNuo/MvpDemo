package com.javatest;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**********************************
 * @Name: OtherTest
 * @Copyright： 个人版权所有
 * @CreateDate： 2019/9/23 14:17
 * @author: HuangFeng
 * @Version： 1.0
 * @Describe:
 *
 **********************************/
public class OtherTest {
    public static void main(String[] args) {
        String s = "卡萨和安康的阿达i话说话阿三大达";
        try {
           // DataOutputStream outputStream = new DataOutputStream(new FileOutputStream("D://1.txt"));
            //            outputStream.write(s.getBytes());
//            outputStream.close();


            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("D://1.txt"));
            writer.write(s);
            writer.write(s);
            writer.write(s+"\n");
            writer.write(s);
            writer.write(s);



            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
//            DataInputStream dataInputStream = new DataInputStream(new FileInputStream("D://1.txt"));
//            System.out.println(dataInputStream.readChar());
//            dataInputStream.close();

            File file =new File("D://1.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
            char [] c = new char[10];
            StringBuilder buffer = new StringBuilder();
            while (inputStreamReader.read(c) != -1){
                buffer.append(c);
                c = new char[10];
            }
            System.out.println(buffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


        IntStream.range(0,100).forEach(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value+"");
            }
        });

//        List<Bean> list = new ArrayList<>();
//        Bean bean = new Bean();
//        bean.setA(1);
//        list.add(bean);
//        System.out.println("list.get(0) = " + list.get(0).getA());
//        bean.setA(2);
//        System.out.println("list.get(0) = " + list.get(0).getA());
    }

}
