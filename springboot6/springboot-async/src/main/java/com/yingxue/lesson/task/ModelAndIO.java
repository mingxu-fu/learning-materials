package com.yingxue.lesson.task;


import com.yingxue.lesson.ES.entity.YongHu;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class ModelAndIO implements Serializable {
    private static ModelAndIO modelAndIO = null;
    public static ModelAndIO getModelAndIO(){
        if (modelAndIO ==null){
            modelAndIO = new ModelAndIO();
        }
        return modelAndIO;
    }


    private static ModelAndIO modelAndIO2 = new ModelAndIO();
    public static ModelAndIO getInstance(){
        String a = "cccc";
        return modelAndIO2;

    }

//    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new FileReader("E:\\file/FileTest01.txt"));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("E:\\file/FileTest02.txt"));
//        String line=null;
//        while((line = bufferedReader.readLine())!=null){
//            bufferedWriter.write(line);
//            System.out.println(line);
//        }
//        bufferedWriter.close();
//        bufferedReader.close();
//
//    }

//    public static void  main(String[] args) throws IOException{
//        FileReader fileReader = new FileReader("E:\\file/FileTest01.txt");
//        FileWriter fileWriter = new FileWriter("E:\\file/FileTest02.txt",true);//append表示追加，不然会从头开始重新写
//        int cout;
//        char content[] = new char[1000];
//        while ((cout=fileReader.read(content))!=-1){
//            System.out.println(cout);
//            System.out.println(new String(content,0,cout));
//
//            fileWriter.write(new String(content,0,cout));
//            fileWriter.write("追加内容");
//            fileWriter.write(new String(content,0,cout));
//        }
//        fileReader.close();
//        fileWriter.close();
//
//
//    }


//    public static void main(String[] args) throws IOException {
//        OutputStream outputStream = new FileOutputStream("E:\\file/FileTest02");
//        String content = "asddfeifjeifjwe";
//        byte b[] = new byte[1024];
//        b=content.getBytes();
//        outputStream.write(b);
//        outputStream.close();
//    }

    public static void main(String[] args) throws Exception {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("E:\\file/FileTest03.txt"));
        YongHu yongHu = new YongHu();
        yongHu.setAge(21);
        yongHu.setJob("chengxu");
        yongHu.setName("fumingxu");
        outputStream.writeObject(yongHu);
        outputStream.close();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("E:\\file/FileTest03.txt"));
        YongHu yongHu1 = (YongHu) objectInputStream.readObject();
        System.out.println(yongHu1.toString());
        objectInputStream.close();



    }
}
