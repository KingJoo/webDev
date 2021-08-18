package dao;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Crawling {
	public static void main(String[] args) {
		Crawling C=new Crawling();
		C.getData();
	}

	public void getData() {
		DAO dao = new DAO();
		try {
			Document doc = Jsoup.connect("https://www.yanolja.com/region/4200000000/ranking?categoryId=REGIONHOMEGW_A_1").get();
			Elements link = doc.select("a[href^=/pension]");
			System.out.println("카테고리 내 상세페이지 개수 : "+link.size());
			for(int i=0;i<link.size();i++) {
				Document doc2 = Jsoup.connect("https://www.yanolja.com"+link.get(i).attr("href")).get();
				//System.out.println(doc2);
				Elements room=doc2.select(".RoomItem_roomItemTitleStyle__KC-YV");//방 .RoomItem_roomItemTitleStyle__KC-YV
				System.out.println("rooms => "+room.text());
				Elements price=doc2.select("s");
				System.out.println("prices => "+price);//
				Elements title=doc2.select(".PageTitle_pageTitle__Q5MEn");
				System.out.println("title = "+title.text());
				//dao.dataInsert(vo);
			}
			/*
			VO vo = new VO();
			vo.setPoster(null);
			vo.setTitle(null);
			vo.setLoc(null);
			vo.setTerm(null);
			vo.setTime(null);
			vo.setGrade(null);
			vo.setPrice(null);
			vo.setActor(null);
			vo.setInfoImg(null);
			
			//Elements title = doc.select("ul.list-toplist-slider span.title");
			//Elements subject = doc.select("ul.list-toplist-slider p.desc");
			//Elements poster = doc.select("ul.list-toplist-slider img.center-croping");
			//Elements link = doc.select(".goods_list a");
			//Elements link2 = doc.getElementsByAttribute("href");
			//System.out.println(link2);
			//System.out.println(link.get(0).attr("href"));
			//System.out.println(link.size());
			//for (int i = 0; i < link.size(); i++) {
				//Document doc2 = Jsoup.connect(link.get(i).attr("href")).get();
				//Elements e = doc2.select(null);
				//System.out.println(title.get(i).text());
				//System.out.println(subject.get(i).text());
				//System.out.println(poster.get(i).attr("data-lazy"));
				//System.out.println(link.get(i).attr("href"));
				//System.out.println("==================================");
				//ConcertVO vo = new ConcertVO();
				//vo.setTitle(title.get(i).text());
				//vo.setSubject(subject.get(i).text());
				//vo.setPoster(poster.get(i).attr("data-lazy"));
				//vo.setLink(link.get(i).attr("href"));
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
