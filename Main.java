
 package Oct19;
 import java.util.*;
 import java.sql.*;

/**
 * @author Eugene Abramov
 */
public class Main {
	
	public static void main(String[] args)
	{
		Employee emp=new Employee();
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Press 1 to Add Employee data\nPress 2 to update Name of Employee\nPress 3 to Delete a Employee record\nPress 4 to Get Name of Employee\nPress 5 to view complete Employee Table\nPress 0 to exit ");
        int choice;
       do {
        	choice=sc.nextInt();
		try{  
			
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sysadmin");
			
			switch(choice)
	         {
	        	 case 1:
	        		 emp.addEmployee(con);
	        		 break;
	        	 case 2:
	        		 emp.updateEmployee(con);
	        		 break;
	        	 case 3:
	        		 emp.deleteEmployee(con);
	        		 break;
	        	 case 4:
	        		 emp.readEmployee(con);
	        	 case 5:
	        		 emp.showAllData(con);
	        		 break;
	        	 case 0:
	        		 System.out.println("-----EXIT-------");
	        		 break;
	        	default:
	        		System.out.println("Wrong Choice");
	        		 
	        		 
	         }
			
		con.close();
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
        } while(choice!=0);
		
	}

}
