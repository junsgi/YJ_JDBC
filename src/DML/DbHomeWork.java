package DML;
import java.sql.*;
import java.util.Scanner;
public class DbHomeWork {
	
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
				System.out.print("\n1번 : 추가\n2번 : 전체 출력\n3번 : 부서번호 삭제\n4번 : 부서이름으로 검색\n5번 : 부서 이름 수정\n입력 : ");
				int com = input.nextInt();
				
				if (com == 1) {
					System.out.print("부서번호 : ");
					int deptno = input.nextInt();
					
					System.out.print("부서이름 : ");
					String deptName = input.next();
					
					System.out.print("층 : ");
					int floor = input.nextInt();
					
					String insert = "INSERT INTO DEPARTMENT "
							+ "VALUES ("+deptno+", '"+deptName+"', "+floor+")";
					int flag = stm.executeUpdate(insert);
					if (flag == 1) {
						System.out.println(insert);
						System.out.println("삽입 성공");
					}else {
						System.out.println("삽입 실패");
					}
					
				}
				else if (com == 2) {
					String query = "SELECT * "
							+ "FROM DEPARTMENT "
							+ "ORDER BY DEPTNO";
					ResultSet rs = stm.executeQuery(query);
					System.out.print(" No  Name  Floor\n");
					while (rs.next()) {
						int n = rs.getInt("DEPTNO");
						String s = rs.getString("DEPTNAME");
						int f = rs.getInt("FLOOR");
						
						System.out.printf("%3d %5s %3d\n", n, s, f);
					}
				}else if (com == 3 ) {
					System.out.print("부서번호 : ");
					int deptno = input.nextInt();
					
					String del = "DELETE FROM DEPARTMENT "
							+ "WHERE DEPTNO = " + deptno;
					int flag = stm.executeUpdate(del);
					if (flag == 1) {
						System.out.println(del);
						System.out.println("삭제 성공");
					}else {
						System.out.println("삭제 실패");
					}
				}else if (com == 4) {
					System.out.print("부서이름 : ");
					String deptName = input.next();
					String query = "SELECT * "
							+ "FROM DEPARTMENT "
							+ "WHERE DEPTNAME = '" + deptName + "'"
							+ " ORDER BY DEPTNO";
					ResultSet rs = stm.executeQuery(query);
					while (rs.next()) {
						int n = rs.getInt("DEPTNO");
						String s = rs.getString("DEPTNAME");
						int f = rs.getInt("FLOOR");
						System.out.print(" No  Name  Floor\n");
						System.out.printf("%3d %5s %3d\n", n, s, f);
					}
				}else if (com == 5) {
					System.out.print("수정 전 부서이름 : ");
					String deptno = input.next();
					
					System.out.print("수정 후 부서이름 : ");
					String deptName = input.next();
					
					String upd = "UPDATE DEPARTMENT "
							+ "SET DEPTNAME = '" + deptName + "'"
									+ " WHERE DEPTNAME = '" + deptno + "'";
					
					int flag = stm.executeUpdate(upd);
					if (flag == 1) {
						System.out.println(upd);
						System.out.println("수정 성공");
					}else {
						System.out.println("수정 실패");
					}
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
