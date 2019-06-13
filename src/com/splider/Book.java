package com.splider;
/**
* @author 曹锡贵
* @version 创建时间：2018年1月13日 下午7:15:50
* 类说明
*/
public class Book {

	//caoxiugi

	private String id;//序号
	private String bookname;//书名
	private String score;//评分
	private String rating_sum;//评价人数
	private String author;//作者
	private String press;//出版社
	private String date;//出版时间
	private String price;//价格
	
	public Book() {
		super();
	}
	public Book(String id, String bookname, String score, String rating_sum, String author, String press, String date,
			String price) {
		super();
		this.id = id;
		this.bookname = bookname;
		this.score = score;
		this.rating_sum = rating_sum;
		this.author = author;
		this.press = press;
		this.date = date;
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getRating_sum() {
		return rating_sum;
	}
	public void setRating_sum(String rating_sum) {
		this.rating_sum = rating_sum;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", bookname=" + bookname + ", score=" + score + ", rating_sum=" + rating_sum
				+ ", author=" + author + ", press=" + press + ", date=" + date + ", price=" + price + "]";
	}
	
	

}
