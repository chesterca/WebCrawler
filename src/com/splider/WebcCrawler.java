package com.splider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author 曹锡贵
 * @version 创建时间：2018年1月13日 下午7:13:43 类说明
 */
public class WebcCrawler {

	// 获取bookUrls
	public List<String> getBookUrl(String url, String booktype) {
		List<String> bookUrls = new ArrayList<String>();
		int index = 0;
		Elements newsHeadlines = null;
		try {
			Map cookies = new HashMap();
			//通过网站来解析一个HTML
			Document doc = Jsoup.connect(url + "/tag/" + booktype + "?start=" + index + "&type=T")
					.header("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)").cookies(cookies)
					.timeout(3000).get();
			newsHeadlines = doc.select("ul").select("h2").select("a");
			Iterator inter = newsHeadlines.iterator();
			while (inter.hasNext()) {
				Element e1 = (Element) inter.next();
				try{
					Thread.sleep(100);//睡眠0.1秒
				}catch (Exception e) {
				}
				
				//睡眠0.1秒,然后在添加得到bookUrls的list中
				bookUrls.add(e1.attr("href"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bookUrls;
	}

	// 获取书本的信息
	public List<Book> getBookInfo(List<String> bookUrls) {
		List<Book> list=new ArrayList<Book>();
		
		Map cookies = new HashMap();
		Iterator inter = bookUrls.iterator();

		int index=0;
		while (inter.hasNext()) {
			String url = (String) inter.next();
			
			
			 String bookName=null;//书名 ....
			 String score=null;//评分
			 String rating_sum=null;//评价人数
			 String author=null;//作者
			 String press=null;//出版社
			 String date=null;//出版时间
			 String price=null;//价格
			try {
				//通过网站来解析一个HTML
				Document doc = Jsoup.connect(url)
						.header("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)").cookies(cookies)
						.timeout(3000).get();
				Elements bookNameElement = doc.getElementsByClass("subject clearfix").select("a");
				Elements scoreElement = doc.select("strong");
				Elements ratingSum = doc.getElementsByClass("rating_sum").select("a").select("span");
				Element infoElement = doc.getElementById("info");

				 bookName = bookNameElement.attr("title");
				 score = scoreElement.html();
				 rating_sum = ratingSum.html();
				
				 author = infoElement.text();
				if (author.indexOf("作者:") > -1) {
					author = infoElement.text().split("作者:")[1].split("出版社:")[0];
				} else {
					author = "";
				}
				
				 press = infoElement.text();
				if (press.indexOf("出版社:") > -1) {
					press = infoElement.text().split("出版社:")[1].split(" ")[1];
				} else {
					press = "";
				}
				 date = infoElement.text();
				if (date.indexOf("出版年:") > -1) {
					date = infoElement.text().split("出版年:")[1].split(" ")[1];
				} else {
					date = "";
				}
				 price = infoElement.text();
				if (price.indexOf("定价:") > -1) {
					price = infoElement.text().split("定价:")[1].split(" ")[1];
					if (price.equals("CNY")) {
						price = infoElement.text().split("定价:")[1].split(" ")[2];
					}
				} else {
					price = "";
				}
				
			} catch (IOException e) {
				//e.printStackTrace();
			}
			
			try{
				Thread.sleep(300);//睡眠0.3秒
			}catch (Exception e) {
			}
			
			
		    //评价数目不低于1000
			if (!rating_sum.equals("") && Integer.parseInt(rating_sum) >= 1000) {
				index++;
				Book book=new Book(Integer.toString(index),bookName, score,rating_sum, author,press,date,price);
				list.add(book);	
			}

		}
		//评分由高到低进行一个排序
		Collections.sort(list,new Comparator<Book>(){

			@Override
			public int compare(Book o1, Book o2) {
				if (Double.parseDouble(o1.getScore()) < Double.parseDouble(o2.getScore())) {
					return 1;
				}

				if (Double.parseDouble(o1.getScore()) == Double.parseDouble(o2.getScore())) {
					return 0;
				}

				return -1;
			}
			
		});
		
		return list;
	}

}
