

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ServletAdd
 */
@WebServlet("/ServletAdd")
public class ServletAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();  
	          
	    int num1=Integer.parseInt(request.getParameter("number1"));  
	    int num2=Integer.parseInt(request.getParameter("number2"));  
	    
	    if(num1 > 0 && num2 > 0)
	    {  
	        RequestDispatcher rd=request.getRequestDispatcher("ServletForword");  
	        rd.forward(request, response);       
	        
	    }  
	    else{  
	        out.print("Something is Wrong Please Enter Valid Number Except 0 ....");  
	        RequestDispatcher rd=request.getRequestDispatcher("/index.html");  
	        rd.include(request, response);
	        }  
	    } 
	

}
