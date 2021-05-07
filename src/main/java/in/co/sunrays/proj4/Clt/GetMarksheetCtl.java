package in.co.sunrays.proj4.Clt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.bean.MarksheetBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.model.MarksheetModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * Get Marksheet functionality Controller. Performs operation for Get Marksheet
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */


@WebServlet(name="GetMarksheetCtl",urlPatterns={"/ctl/GetMarksheetCtl"})

public class GetMarksheetCtl extends BaseCtl {

	private static Logger log=Logger.getLogger(GetMarksheetCtl.class);
	
	
	
	protected boolean validate(HttpServletRequest request){
		
		log.debug("GetMarksheetCTL Method validate Started");

		boolean pass = true;
		
		if(DataValidator.isNull(request.getParameter("rollNo"))){
			
			request.setAttribute("rollno",PropertyReader.getValue("error.require","Roll Number"));
			pass=false;
		}
		
		
		log.debug("GetMarksheetCTL Method validate Ended");
		
		return pass;
	}
	
	protected BaseBean populateBean(HttpServletRequest request){
		
		log.debug("GetMarksheetCtl Method populatebean Started");
		System.out.println("ddddd");
		MarksheetBean bean=new MarksheetBean();
		
		
		bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));
		
		
		 log.debug("GetMarksheetCtl Method populatebean Ended");
		
		 return bean;
		
		
	}
	
	/**
     * Concept of Display method
     * 
     */
    
       
   
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			System.out.println("do get");
			
			ServletUtility.forward(getView(), request, response);
	}
		
		
		 /**
	     * Concept of Submit Method
	     * 
	     */
	    

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
			
	log.debug("GetMarksheetCtl Method doGet Started");
	String op=DataUtility.getString(request.getParameter("operation"));
			

			MarksheetModel model=new MarksheetModel();
			
			MarksheetBean bean=(MarksheetBean)populateBean(request);
			
			//long id=DataUtility.getLong(request.getParameter("id"));
			
			if(OP_GO.equalsIgnoreCase(op)){
				
				try {
					bean=model.findByRollNo(bean.getRollNo());
					if(bean!=null){
						ServletUtility.setBean(bean, request);
					}
					else{
						ServletUtility.setErrorMessage("Roll Number Does Not exists !!", request);
						
					}
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					log.error(e);
					ServletUtility.handleException(e, request, response);
					return;

				}
				
				
			}else if(OP_RESET.equalsIgnoreCase(op)){
				
				ServletUtility.redirect(ORSView.GET_MARKSHEET_CTL, request, response);
				return;
			}
			
			ServletUtility.forward(getView(), request, response);
			log.debug("MarksheetCtl Method doGet Ended");
			
	}
	
	 
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.GET_MARKSHEET_VIEW;
	}

}
