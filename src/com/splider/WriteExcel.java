package com.splider;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author 曹锡贵
 * @version 创建时间：2018年1月13日 下午7:15:38 类说明
 */
public class WriteExcel {
	public void writeExcel(List<Book> list) {
		// 创建HSSFWorkbook对象
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建HSSFSheet对象
		HSSFSheet sheet = wb.createSheet("sheet0");

		// 创建HSSFRow对象
		HSSFRow row = sheet.createRow(0);
		// 创建HSSFCell对象
		HSSFCell cell0 = row.createCell(0);
		HSSFCell cell1 = row.createCell(1);
		HSSFCell cell2 = row.createCell(2);
		HSSFCell cell3 = row.createCell(3);
		HSSFCell cell4 = row.createCell(4);
		HSSFCell cell5 = row.createCell(5);
		HSSFCell cell6 = row.createCell(6);
		HSSFCell cell7 = row.createCell(7);
		// 设置单元格的值
		cell0.setCellValue("序号");
		cell1.setCellValue("书名");
		cell2.setCellValue("评分");
		cell3.setCellValue("评价人数");
		cell4.setCellValue("作者");
		cell5.setCellValue("出版社");
		cell6.setCellValue("出版日期");
		cell7.setCellValue("价格");

		for (int i = 0; i < list.size(); i++) {
			// 创建HSSFRow对象
			row = sheet.createRow(i + 1);
			// 创建HSSFCell对象
			cell0 = row.createCell(0);
			cell1 = row.createCell(1);
			cell2 = row.createCell(2);
			cell3 = row.createCell(3);
			cell4 = row.createCell(4);
			cell5 = row.createCell(5);
			cell6 = row.createCell(6);
			cell7 = row.createCell(7);
			// 设置单元格的值
			cell0.setCellValue(list.get(i).getId());
			cell1.setCellValue(list.get(i).getBookname());
			cell2.setCellValue(list.get(i).getScore());
			cell3.setCellValue(list.get(i).getRating_sum());
			cell4.setCellValue(list.get(i).getAuthor());
			cell5.setCellValue(list.get(i).getPress());
			cell6.setCellValue(list.get(i).getDate());
			cell7.setCellValue(list.get(i).getPrice());
		}
		// 输出Excel文件
		FileOutputStream output = null;
		try {
			output = new FileOutputStream("bookInfo.xls");
			wb.write(output);
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
