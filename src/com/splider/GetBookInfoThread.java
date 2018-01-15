package com.splider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
* @author 曹锡贵
* @version 创建时间：2018年1月15日 下午7:37:22
* 类说明
*/
public class GetBookInfoThread implements Callable<List<Book>>{

	//声明一个webCrawler获取getBookInfo方法
	WebcCrawler webCrawler=new WebcCrawler();
	
	private  List<String> bookUrls;	
	public GetBookInfoThread(List<String> bookUrls){
		this.bookUrls=bookUrls;
	}
	
	@Override
	public List<Book> call() throws Exception {
		List<Book> booklist=webCrawler.getBookInfo(bookUrls);
		return booklist;
	}

}
