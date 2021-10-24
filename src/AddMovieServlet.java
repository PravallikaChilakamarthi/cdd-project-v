import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbconnection.DBConnection;

@WebServlet("/addmovie") 

public class AddMovieServlet extends HttpServlet
{

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		
		res.setContentType("text/html");
		
		PrintWriter out = res.getWriter();
		
		String moviename = req.getParameter("moviename"); 
		String[] Type = req.getParameterValues("Type");
		
		String Type1="";
		for(String value: Type) {
			Type1=Type+value+",";
		}
		System.out.println(Type1);
		
		String genre = req.getParameter("description");
		
		
		
		try {
			
			Connection con= DBConnection.getConnection();
			
			PreparedStatement psmt=con.prepareStatement("insert into movie(moviename,Type,description) values(?,?,?)");
		
			psmt.setString(1, moviename);
			psmt.setString(2, Type1);
			psmt.setString(3, genre);
//			psmt.setString(4, director);
//			psmt.setString(5, producer);
//			psmt.setString(6, cast);
//			psmt.setString(7, rdate);
//			psmt.setString(8, cbrating);
			
			int i=psmt.executeUpdate();
			
			if(i>0) 
			{
				
				out.println("<b>Movie Added Successfully</b><br>");
	    		out.println("<a href=addmovie.html> Add Another</a>");
				
			}
		
		}
		catch(Exception e)
		{
			out.println(e);
		}
		
	}
	
	
	
}
 