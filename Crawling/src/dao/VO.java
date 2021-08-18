package dao;
import lombok.*;
import java.util.*;
@Getter
@Setter
public class VO {
	private long isbn;
	private String category,subCategory,poster,title,subtitle,writer,publisher,etcInfo,price,discount,text,imgs,contentsTable,tags,publicationDay;
}