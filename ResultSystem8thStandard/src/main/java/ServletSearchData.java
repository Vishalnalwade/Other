

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
 * Servlet implementation class ServletSearchData
 */
@WebServlet("/ServletSearchData")
public class ServletSearchData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSearchData() {
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
		// retrive from data
        int rollNumber=Integer.parseInt(request.getParameter("rollNumber"));

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
            String sql = "SELECT * FROM data WHERE roll_number = ?";
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1, rollNumber);

            response.setContentType("text/html");
            PrintWriter out=response.getWriter();
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                // out.println("Roll No : " + rs.getInt("roll_number"));
                // out.println(" Student Name : " + rs.getString("student_name"));
                // out.println("Date of Birth : " + rs.getString("d_o_b"));
                // out.println("Gender : " + rs.getString("gender"));
                // out.print("---------------------------------");
                int ro=rs.getInt("roll_number");
                String n=rs.getString("student_name");
                String d=rs.getString("date_of_birth");
                String g=rs.getString("gender");
                if(rollNumber == ro){
                    out.print("<html><head><link rel='stylesheet' type='text/css' href='Background.css'></head><body>");
                    out.print("<h2>Student Data</h2>");
                    out.print("<table border='1' style='border-collapse:collapse;width:100%'><tr><th>Roll Number</th><th>Student Name</th><th>Date of Birth</th><th>Gender</th></tr>");
                    out.print("<tr><td>"+ro+"</td><td>"+n+"</td><td>"+d+"</td><td>"+g+"</td></tr>");
                    out.print("</table>");
                    out.print("</body></html>");
                }
                else{
                    out.print("This Data is Not Available in Database ..!");
                }
            }



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

}
