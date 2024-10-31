

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
 * Servlet implementation class ServletDisplayMark
 */
@WebServlet("/ServletDisplayMark")
public class ServletDisplayMark extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDisplayMark() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            String sql = "SELECT * FROM mark";
            PreparedStatement statement=connection.prepareStatement(sql);

            response.setContentType("text/html");
            PrintWriter out=response.getWriter();
            ResultSet rs=statement.executeQuery();
                    out.print("<html><head><link rel='stylesheet' type='text/css' href='Background.css'></head><body>");
                    out.print("<h2>Student Data</h2>");
                    out.print("<table border='1' style='border-collapse:collapse;width:100%'><tr><th>Roll Number</th><th>Marathi</th><th>Hindi</th><th>Maths</th><th>Science</th><th>English</th><th>Total Marks</th><th>Percentage</th><th>Result</th></tr>");
            while(rs.next()){
            
                
                // String r=rs.getString("result");
                
                    out.print("<tr><td>"+rs.getInt("roll_number")+"</td><td>"+rs.getInt("marathi")+"</td><td>"+rs.getInt("hindi")+"</td><td>"+rs.getInt("maths")+"</td><td>"+rs.getInt("science")+"</td><td>"+rs.getInt("english")+"</td><td>"+rs.getInt("total_marks")+"</td><td>"+rs.getDouble("percentage")+"</td><td>"+rs.getString("result")+"</td></tr>");
                
            }
                    out.print("</table>");
                    out.print("</body></html>");


            // close the connection
            statement.close();
            connection.close();
        }
        catch(ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            response.getWriter().print("<h4>Error in: "+ e.getMessage() +"</h4>");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
