package dao;

import java.util.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MelonReplyVO {
	private int rno,mno;
	private String name,msg,dbday; // dbday-날짜를 문자열로 받기위한 변수
	private Date regdate;
}