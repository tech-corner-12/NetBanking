package test;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
@SuppressWarnings("serial")
@WebServlet("/hello")
public class Bill extends GenericServlet{
	public Connection con;
	@Override
	public void init()throws ServletException{
		con=DBConnection.getcon();
	}
   @Override
   public void service(ServletRequest req,ServletResponse res)throws ServletException,IOException{
	   PrintWriter pw=res.getWriter();
	   res.setContentType("text/html");
	   String cardno=req.getParameter("cardno");
	   String mobileno=req.getParameter("mobileno");
	   String email=req.getParameter("email");
	   int amounttopay=Integer.parseInt(req.getParameter("amounttopay"));
	   String dt=req.getParameter("dt");
	   try
	   {
		   PreparedStatement ps=con.prepareStatement("insert into bill values(?,?,?,?,?)");
		   ps.setString(1, cardno);
		   ps.setString(2, mobileno);
		   ps.setString(3, email);
		   ps.setInt(4, amounttopay);
		   ps.setString(5, dt);
		   int k=ps.executeUpdate();
		   if(k>0)
		   {
			   pw.println("Card NO:" +cardno+"<br>");
			   pw.println("Mobile NO:" +mobileno+"<br>");
			   pw.println("Amount Paid:" +amounttopay+"<br>");
			   pw.println("Date:" +dt+"<br>");
			   pw.println("Bill Paid Successfull");
		   }
	   }catch(Exception e)
	   {e.printStackTrace();}
	   }
}
