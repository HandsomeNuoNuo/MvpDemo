package com.javatest;

import com.ecg.process.Processor;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**********************************
 * @Name: BinDecodeUtil
 * @Copyright： CreYond
 * @CreateDate： 2019/12/31 16:31
 * @author: HuangFeng
 * @Version： 1.0
 * @Describe:
 *
 **********************************/
public class BinDecodeUtil {

    public static void main(String[] args) {
        decode();
    }


    public static void decode(){
//        try {
////            DataInputStream dataInputStream = new DataInputStream(new FileInputStream("D://1.txt"));
////            System.out.println(dataInputStream.readChar());
////            dataInputStream.close();
//
//            File file =new File("D://Work//binDecode//1298_10087_20191231104759_evaluate.bin");
//            int length = (int) (file.length()/64);
//            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
//            Processor processor = new Processor();
//            byte [] b = new byte[64];
//            for (int i = 0; i < length;i++){
//                bufferedInputStream.read(b);
//                Data data = _1C_V2_Converter.converter(b);
//
//                System.out.println(data.index+"");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
