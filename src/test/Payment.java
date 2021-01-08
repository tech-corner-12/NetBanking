package test;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
@SuppressWarnings("serial")
@WebServlet("/pay")
public class Payment extends GenericServlet{
	public Connection con;
	@Override
	public void init()throws ServletException{
		con=DBConnection.getcon();
	}
   @Override
   public void service(ServletRequest req,ServletResponse res)throws ServletException,IOException{
	   PrintWriter pw=res.getWriter();
	   res.setContentType("text/html");
	   String accountno=req.getParameter("accountno");
	   String bname=req.getParameter("bname");
	   String bno1=req.getParameter("bno1");
	   int amount=Integer.parseInt(req.getParameter("amount"));
	   String dt=req.getParameter("dt");
	   try
	   {
		   PreparedStatement ps=con.prepareStatement("insert into netprachi values(?,?,?,?,?)");
		   ps.setString(1, accountno);
		   ps.setString(2, bname);
		   ps.setString(3, bno1);
		   ps.setInt(4, amount);
		   ps.setString(5, dt);
		   int k=ps.executeUpdate();
		   if(k>0)
		   {
			   pw.println("Beneficiary Name:" +bname+"<br>");
			   pw.println("Beneficiary Account NO.:" +bno1+"<br>");
			   pw.println("Amount:" +amount+"<br>");
			   pw.println("Date"+dt+"<br>");
			   pw.println("Transfer Successfull");
		   }
	   }catch(Exception e)
	   {e.printStackTrace();}
	   }
}
