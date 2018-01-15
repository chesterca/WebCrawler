package com.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.splider.Book;
import com.splider.WebcCrawler;
import com.splider.WriteExcel;

/**
* @author 曹锡贵
* @version 创建时间：2018年1月13日 下午7:16:09
* 类说明
*/

//没有加线程进行保护的测试方法
public class Test01 {
	
	public static void main(String[] args) {
		TestCrawler();
	}
	
	public static void TestCrawler(){
		String url="https://book.douban.com";
		String[] booktypes={"互联网","编程","算法"};
		List<Book> booklists=new ArrayList<Book>();
		
		WebcCrawler webCrawler=new WebcCrawler();
		WriteExcel  writeExcel=new WriteExcel();
		
		for(int i=0;i<booktypes.length;i++){
			List<String> bookUrls=webCrawler.getBookUrl(url, booktypes[i]);
			List<Book> booklist=webCrawler.getBookInfo(bookUrls);
			System.out.println(booklist);
			booklists.addAll(booklist);
		}
		writeExcel.writeExcel(booklists);
	}

}
