package dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodVO {
	private int no,cno,good,soso,bad; // rs.getInt()
    private double score;// rs.getDouble()
    private String name,address,tel,type,price,parking,time,menu,poster; // rs.getString()
}
