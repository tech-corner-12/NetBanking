package test;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
@SuppressWarnings("serial")
@WebServlet("/log")
public class Loginservlet extends HttpServlet{
	public Connection con;
	@Override
	public void init()throws ServletException{
		con=DBConnection.getcon();
	}
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		String name=req.getParameter("name");
		String password=req.getParameter("password");
		try{
			PreparedStatement ps=con.prepareStatement("select * from netcustomer where name=? and password=?");
			ps.setString(1, name);
			ps.setString(2, password);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
            	HttpSession hs=req.getSession();
            	hs.setAttribute("name",rs.getString(1));
            	RequestDispatcher rd=req.getRequestDispatcher("index.html");
            	rd.forward(req, res);
            	
            }
            else
            {
            	RequestDispatcher rd=req.getRequestDispatcher("error.jsp");
            	rd.forward(req, res);
            }
		}catch(Exception e){e.printStackTrace();}
	}
}
