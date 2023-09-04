import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class Main {
	
	public static Connection makeConnection() {
		String url = "jdbc:oracle:thin:@10.30.3.95:1521:orcl";
		String user = "c##2201127";
		String pw = "p2201127";
		Connection con = null;
		try {
			/**  1. JDBC 드라이버를 적재 */
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이브 적재 성공");
			
			/** 2. DB Connection */
			con = DriverManager.getConnection(url, user, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("드라이브 적재 실패");

		} catch (Exception e) {
			System.out.println("fail!");
			e.printStackTrace();
		}
		return con;
	}
	

	public static void main(String[] args) {
		
		Connection con = makeConnection();
		Statement stm = null;
		
		try {
			/* 3. SQL 문장 작성 및 전송
			 * 	SQL 문장 작성은 Statement 객체
			 * */
			 stm = con.createStatement();
			 
			 // 전송
			 ResultSet rs = stm.executeQuery(
					 "SELECT * "
					 + "FROM BOOKS "
					 + "order by book_id"
					 );
			 
			 // 4. 결과집합 사용후 연결 해제
			 // 첫 번째 레코드로 커서 이동
			 // next() 메소드 : 첫 번째 레코드부터 차례대로 다음으로 넘어감
			 
			 rs.next(); // 첫 번째 레코드에 커서 위치
			 System.out.println(rs.getString("title"));
			 
			 rs.next(); // 두 번째 레코드에 커서 위치
			 System.out.println(rs.getString("title"));
			 System.out.println(rs.getInt("year"));
			 
			 while (rs.next()) {
				 int n = rs.getInt("book_id");
				 String name = rs.getString("title");
				 int year = rs.getInt("year");
				 System.out.println();
				 System.out.println("book_id : " + n + " \nname : " + name + " \nyear : " + year);
			 }
		}catch (Exception e) {
			System.out.println("Statement 객체 생성 불가능 ;;");
		}
		
		
		
	}

}
