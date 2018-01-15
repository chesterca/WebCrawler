package com.splider;

import java.util.List;

/**
 * @author 曹锡贵
 * @version 创建时间：2018年1月14日 下午9:39:49 类说明
 */
public class BookUrlThread extends Thread {
	
	//声明一个webCrawler获取getBookUrl方法
	WebcCrawler webCrawler=new WebcCrawler();
	
	private static List<String> bookUrls;
	private String url;
	private String booktype;
	private static Object ob = "aa";//值是任意的
	
	//构造方法，进行参数的赋值
	public BookUrlThread(String url,String booktype ) {
		this.url=url;
		this.booktype=booktype;
	}
	
	@Override
	public void run() {
		 synchronized (ob) {//加个锁
		    bookUrls=webCrawler.getBookUrl(url, booktype);
		 }
	}

	//get方法得到返回list的值
	public static List<String> getBookUrls() {
		return bookUrls;
	}
	

}
