package Oct19;

/**
 * @author Eugene Abramov
 */
import java.util.*;

import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import oracle.sql.STRUCT;

import java.sql.*;

public class Employee {
	
	static Scanner sc=new Scanner(System.in);
	
	void addEmployee(Connection con)
	{
		try
		{
		String name;
	    int salary;
		
	    System.out.println("Enter Name");
		name=sc.next();
		System.out.println("Enter salary");
		salary=sc.nextInt();
		
		CallableStatement csmt = con.prepareCall("{call Create_employee(?,?)}");
		csmt.setString(1,name);
		csmt.setInt(2, salary);
		
		int r=csmt.executeUpdate();
		if(r>0)
		{
			System.out.println("Employee added successfully !!!!!!");
		}
		
		csmt.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
	void updateEmployee(Connection con)
	{
		try
		{
		String name;
		int id;
		
	    System.out.println("Enter Employee ID whose name you want to update");
		id=sc.nextInt();
		System.out.println("Enter Name to be Updated");
		name=sc.next();
		
		CallableStatement csmt = con.prepareCall("{call update_employee(?,?)}");
		csmt.setString(2,name);
		csmt.setInt(1,id);
		
		int r=csmt.executeUpdate();
		if(r>0)
		{
			System.out.println("Employee Name Updated successfully !!!!!!");
		}
		
		csmt.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	void deleteEmployee(Connection con)
	{
		try
		{
		int id;
		
	    System.out.println("Enter Employee ID to delete the record");
		id=sc.nextInt();
		
		CallableStatement csmt = con.prepareCall("{call delete_employee(?)}");
		csmt.setInt(1,id);
		
		int r=csmt.executeUpdate();
		if(r>0)
		{
			System.out.println("Employee removed successfully !!!!!!");
		}
		
		csmt.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	void readEmployee(Connection con)
	{
		try
		{
		int id;
		
	    System.out.println("Enter Employee ID to get the name and salary");
		id=sc.nextInt();

		CallableStatement csmt=(CallableStatement)con.prepareCall("{call ReadEmployee(?,?)}");
        csmt.setInt(1,id);
        csmt.registerOutParameter(2, OracleTypes.CURSOR);
         
        csmt.execute();
        OracleResultSet rs=(OracleResultSet)csmt.getObject(2);
        while(rs.next())
            System.out.println("Name : "+rs.getString("name")+" "+"Salary : "+rs.getInt("salary"));          
		
		csmt.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	void showAllData(Connection con)
	{
		try
		{
		
		CallableStatement csmt=(CallableStatement)con.prepareCall("{call AllData(?)}");
        csmt.registerOutParameter(1, OracleTypes.CURSOR);
         
        csmt.execute();
        OracleResultSet rs=(OracleResultSet)csmt.getObject(1);
        System.out.println("EmpId Name Salary"); 
        while(rs.next())
            System.out.println(rs.getInt("empId")+" "+rs.getString("name")+" "+rs.getInt("salary"));          
		
		csmt.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
