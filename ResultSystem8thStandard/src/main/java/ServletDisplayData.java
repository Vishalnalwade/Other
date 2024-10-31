

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class ServletDisplayData
 */
@WebServlet("/ServletDisplayData")
public class ServletDisplayData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDisplayData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Database connection setup
        String url="jdbc:mysql://localhost:3306/student";
        String user="root";
        String password="1234";

        try{
            // load mysql jdbc driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish Connection
            Connection connection=DriverManager.getConnection(url,user,password);

            // insert data into stud table
            String sql = "SELECT * FROM data";
            PreparedStatement statement=connection.prepareStatement(sql);

            response.setContentType("text/html");
            PrintWriter out=response.getWriter();
            ResultSet rs=statement.executeQuery();
                    out.print("<html><head><link rel='stylesheet' type='text/css' href='Background.css'></head><body>");
                    out.print("<h2>Student Data</h2>");
                    out.print("<table border='1' style='border-collapse:collapse;width:100%'><tr><th>Roll Number</th><th>Student Name</th><th>Date of Birth</th><th>Gender</th></tr>");
            while(rs.next()){
                                
                    out.print("<tr><td>"+rs.getInt("roll_number")+"</td><td>"+rs.getString("student_name")+"</td><td>"+rs.getString("date_of_birth")+"</td><td>"+rs.getString("gender")+"</td></tr>");
                }
                    out.print("</table>");
                    out.print("</body></html>");

                    
                statement.close();
                connection.close();
            }

            // Execute the insert Operation
            // int rowInserted=statement.executeUpdate();

            // generate responce
        
            // if(rowInserted > 0)
            // {
            //     out.print("<h4>Student data Deleted Successfully ....!!</h4>");
            //     out.print("<br>");
            //     // out.print("<a href='index.html'></a>");
            //     RequestDispatcher rd=request.getRequestDispatcher("/index.html");
            //     rd.include(request,responce);
            // }
            // else
            // {
            //     out.print("<h4>Error in Deleting student data ..!</h4>");
            //     out.print("<br>");
            //     RequestDispatcher rd=request.getRequestDispatcher("/index.html");
            //     rd.include(request,responce);
            // }

            // close the connection
            
        
        catch(ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            response.getWriter().print("<h4>Error in: "+ e.getMessage() +"</h4>");
        }
	}

}
