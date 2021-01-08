package test;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
@SuppressWarnings("serial")
@WebServlet("/card")
public class New extends GenericServlet{
	public Connection con;
	@Override
	public void init()throws ServletException{
		con=DBConnection.getcon();
	}
   @Override
   public void service(ServletRequest req,ServletResponse res)throws ServletException,IOException{
	   PrintWriter pw=res.getWriter();
	   res.setContentType("text/html");
	   String name=req.getParameter("name");
	   String password=req.getParameter("password");
	   try
	   {
		   PreparedStatement ps=con.prepareStatement("insert into netcustomer values(?,?)");
           ps.setString(1, name);
           ps.setString(2, password);
		   int k=ps.executeUpdate();
		   if(k>0)
		   {
			pw.println("Registration Successful");
			
		   }
	   }catch(Exception e)
	   {e.printStackTrace();}
	   }
}
