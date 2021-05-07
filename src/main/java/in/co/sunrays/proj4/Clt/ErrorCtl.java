package in.co.sunrays.proj4.Clt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.sunrays.proj4.util.ServletUtility;

/**
 * Servlet implementation class ErrorCtl
 */
@WebServlet(name="ErrorCtl",urlPatterns={"/ErrorCtl"})
public class ErrorCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	  ServletUtility.forward(getView(), request, response);
	  System.out.println("inside error ctl do get");
	}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 ServletUtility.forward(getView(), request, response);
			  System.out.println("inside error ctl do post");
	}

		@Override
		protected String getView() {
			  System.out.println("before view");
			return ORSView.ERROR_VIEW;
			 
		}

}
