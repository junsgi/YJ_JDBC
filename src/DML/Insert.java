package DML;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;
public class Insert {
	public static Connection makeConn() {
		String url = "jdbc:oracle:thin:@10.30.3.95:1521:orcl";
		String id = "c##2201127";
		String pw = "p2201127";
		Connection con = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, id, pw);
			System.out.println("연결 성공");
		}catch (ClassNotFoundException e){
			System.out.println("적재 실패");
		}catch (Exception e){
			System.out.println(e);
		}
		return con;
	}
	
	public static void dml(Connection con) {
		try {
			Statement stm = con.createStatement();
			Scanner input = new Scanner(System.in);
			while (true) {
				System.out.print("id : ");
				String id = input.next();
				if (id.equals("-1")) break;
				System.out.print("title : ");
				String title = input.next();
				
				
				String insert = "INSERT INTO BOOKS "
						+ "VALUES("+id+", '"+title+"', '영진', '2023', 59000)";
				// 자동 커밋
				int flag = stm.executeUpdate(insert);
				
				System.out.println(insert);
				if (flag == 1) {
					System.out.println("레코드 추가 성공");
				}else {
					System.out.println("레코드 추가 실패");
				}
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) {
		dml(makeConn());
	}

}
