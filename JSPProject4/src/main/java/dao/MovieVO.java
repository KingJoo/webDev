package dao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieVO {
	private int mno;
    private String title,genre,poster,actor,regdate,grade,director;
}
