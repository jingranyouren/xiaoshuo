package com.logytj.ebook.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadFileUtils {
	static public String readFile(String  path) {      

	    try {
	            FileInputStream fileInputStream = new FileInputStream(new File(path));
	            // 把每次读取的内容写入到内存中，然后从内存中获取
	            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	            byte[] buffer = new byte[1024*50];
	            int len = 0;
	            // 只要没读完，不断的读取
	            while ((len = fileInputStream.read(buffer)) != -1) {
	                outputStream.write(buffer, 0, len);
	            }
	            // 得到内存中写入的所有数据
	            byte[] data = outputStream.toByteArray();
	            fileInputStream.close();
	            //return new String(data);
	            return new String(data, "GBK");//以GBK（什么编码格式）方式转
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return null;
	    }
}
