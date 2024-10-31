

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class ServletDisplayAll
 */
@WebServlet("/ServletDisplayAll")
public class ServletDisplayAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDisplayAll() {
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
            // String sql1 = "SELECT * FROM sdata";
            // Statement statement1=connection.createStatement();
            // ResultSet rs1=statement1.executeQuery(sql1);

            String sql = "SELECT d.roll_number,d.student_name,d.date_of_birth, d.gender,m.marathi,m.hindi,m.maths,m.science,m.english,m.total_marks,m.percentage,m.result FROM data AS d JOIN mark AS m ON d.roll_number = m.roll_number";
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(sql);
            


            response.setContentType("text/html");
            PrintWriter out=response.getWriter();
                    out.print("<html><head><link rel='stylesheet' type='text/css' href='Background.css'></head><body>");
                    out.print("<h2>Student Information</h2>");
                    out.print("<table border='1' style='border-collapse:collapse;width:100%'><tr><th>Roll Number</th><th>Student Name</th><th>Date of Birth</th><th>Gender</th><th>Marathi</th><th>Hindi</th><th>Maths</th><th>Science</th><th>English</th><th>Total Marks</th><th>Percentage</th><th>Result</th></tr>");

                    while(rs.next()){
                        int rno=rs.getInt("roll_number");
                        String sn=rs.getString("student_name");
                        String d=rs.getString("date_of_birth");
                        String g=rs.getString("gender");
                    
                        int ma=rs.getInt("marathi");
                        int hi=rs.getInt("hindi");
                        int mt=rs.getInt("maths");
                        int sc=rs.getInt("science");
                        int en=rs.getInt("english");
                        int tm= rs.getInt("total_marks");
                        double pr= rs.getDouble("percentage");
                        String re=rs.getString("result");
                    
            
                
                // String r=rs.getString("result");
                
                    out.print("<tr><td>"+rno+"</td><td>"+sn+"</td><td>"+d+"</td><td>"+g+"</td><td>"+ma+"</td><td>"+hi+"</td><td>"+mt+"</td><td>"+sc+"</td><td>"+en+"</td><td>"+tm+"</td><td>"+pr+"</td><td>"+re+"</td></tr>");
                
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
