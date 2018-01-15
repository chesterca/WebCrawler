package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import com.splider.Book;
import com.splider.BookUrlThread;
import com.splider.GetBookInfoThread;
import com.splider.WebcCrawler;
import com.splider.WriteExcel;

/**
* @author 曹锡贵
* @version 创建时间：2018年1月15日 下午7:16:09
* 类说明
*/
public class Test02 {
	
	public static void main(String[] args) {
		TestCrawler();
	}
	
	public static void TestCrawler(){
		String url="https://book.douban.com";
		String[] booktypes={"互联网","编程","算法"};
		List<Book> booklists=new ArrayList<Book>();
		
        //声明一个写入Excel表中的类
		WriteExcel  writeExcel=new WriteExcel();
		
		
		for(int i=0;i<booktypes.length;i++){
			//开启书本地址的线程
			BookUrlThread bookUrlThread=new BookUrlThread(url,booktypes[i]);
			bookUrlThread.run();
			//得到书本地址的一个list
			List<String> bookUrls = bookUrlThread.getBookUrls();
			System.out.println(bookUrls);
			
			//开启获取书本信息的线程
			 ExecutorService executorService=Executors.newCachedThreadPool();  
			 Callable<List<Book>> callable=new GetBookInfoThread(bookUrls);  
			 Future future=executorService.submit(callable);
			  
			  
			List<Book> booklist=null;
			try {
				//获取书本信息booklist
				booklist = (List<Book>) future.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			System.out.println(booklist);
			//每循环一次进行booklist的添加
			booklists.addAll(booklist);
		}
		
		//把booklists写到Excel表中
		writeExcel.writeExcel(booklists);
	}

}
