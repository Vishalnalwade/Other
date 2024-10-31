

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
 * Servlet implementation class ServletDeleteMark
 */
@WebServlet("/ServletDeleteMark")
public class ServletDeleteMark extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDeleteMark() {
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
            String sql = "DELETE FROM  mark WHERE roll_number = ?";
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1, rollNumber);

            // Execute the insert Operation
            int rowInserted=statement.executeUpdate();

            // generate response
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();
        
            if(rowInserted > 0)
            {
                out.print("<script>");
                out.print("alert('Data Deleted Successfully ...!');");
                out.print("</script>");
                // out.print("<a href='index.html'></a>");
                RequestDispatcher rd=request.getRequestDispatcher("/index.html");
                rd.include(request,response);
            }
            else
            {
                out.print("<script>");
                out.print("alert('Error in Deleting Data..!');");
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
