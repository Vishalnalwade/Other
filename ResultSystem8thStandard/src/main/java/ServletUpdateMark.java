

import jakarta.servlet.RequestDispatcher;
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
import java.sql.SQLException;

/**
 * Servlet implementation class ServletUpdateMark
 */
@WebServlet("/ServletUpdateMark")
public class ServletUpdateMark extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUpdateMark() {
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
        float marathi=Integer.parseInt(request.getParameter("sub1"));
        float hindi=Integer.parseInt(request.getParameter("sub2"));
        float maths=Integer.parseInt(request.getParameter("sub3"));
        float science=Integer.parseInt(request.getParameter("sub4"));
        float english=Integer.parseInt(request.getParameter("sub5"));
        

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
            String sql="UPDATE mark SET  marathi=?, hindi=?,maths=?,science=?,english=?, total_marks=?,percentage=?,result=? WHERE roll_number = ?";            PreparedStatement statement=connection.prepareStatement(sql);

            double totalMarks= marathi + hindi + maths + science + english;
            double percentage = (double)(totalMarks / 500) * 100;
            String result=percentage >=35 ? "pass" : "fail";

            
            statement.setFloat(1, marathi);
            statement.setFloat(2, hindi);
            statement.setFloat(3, maths);
            statement.setFloat(4, science);
            statement.setFloat(5, english);
            statement.setDouble(6, totalMarks);
            statement.setDouble(7, percentage);
            statement.setString(8, result);
            statement.setInt(9, rollNumber);

            // Execute the insert Operation
            int rowInserted=statement.executeUpdate();

            // generate response
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();
        
            if(rowInserted > 0)
            {
                out.print("<script>");
                out.print("alert('Marks Inserted Successfully ...!');");
                out.print("</script>");
                // out.print("<a href='index.html'></a>");
                RequestDispatcher rd=request.getRequestDispatcher("/index.html");
                rd.include(request,response);
            }
            else
            {
                out.print("<script>");
                out.print("alert('Error Inserting Marks ...!');");
                out.print("</script>");
                RequestDispatcher rd=request.getRequestDispatcher("/index.html");
                rd.include(request,response);
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
