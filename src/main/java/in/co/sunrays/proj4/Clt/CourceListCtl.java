package in.co.sunrays.proj4.Clt;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.bean.CourceBean;
import in.co.sunrays.proj4.bean.RoleBean;
import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.model.CourceModel;
import in.co.sunrays.proj4.model.RoleModel;
import in.co.sunrays.proj4.model.SubjectModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;


@WebServlet(name="CourceListCtl",urlPatterns={"/ctl/CourceListCtl"})
public class CourceListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

protected void preload(HttpServletRequest request){
		
		CourceModel model=new CourceModel();
		List l = null;
		
		
			try {
				 l = model.list();
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
		 request.setAttribute("courceName",l);
			
	}
	
	
	
	
	
	
	private static Logger log = Logger.getLogger(RoleListCtl.class);

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
        CourceBean bean = new CourceBean();
      //  System.out.println("popppp  "+request.getParameter("courceId"));
        bean.setId(DataUtility.getLong(request.getParameter("courceId")));
        populateDTO(bean, request);

        return bean;
        
    }

 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		
 		log.debug("RoleListCtl doGet Start");
        List list = null;
        int pageNo = 1;
        int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
        CourceBean bean = new CourceBean();
    //    String op = DataUtility.getString(request.getParameter("operation"));
        CourceModel model = new CourceModel();
        try {
            list = model.search(bean, pageNo, pageSize);
            ServletUtility.setList(list, request);
            if (list == null || list.size() == 0) {
                ServletUtility.setErrorMessage("No record found ", request);
            }
            ServletUtility.setList(list, request);
            ServletUtility.setPageNo(pageNo, request);
            ServletUtility.setPageSize(pageSize, request);
            ServletUtility.forward(getView(), request, response);
        } catch (ApplicationException e) {
            log.error(e);
            ServletUtility.handleException(e, request, response);
            return;
        }
        log.debug("RoleListCtl doGet End");
   
 		
 		
			}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.debug("CourceListCtl doPost Start");
        List list = null;
        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
        pageNo = (pageNo == 0) ? 1 : pageNo;
        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
                .getValue("page.size")) : pageSize;
        CourceBean bean = (CourceBean) populateBean(request);
        String op = DataUtility.getString(request.getParameter("operation"));
        
       CourceModel model = new CourceModel();

        try {

        	 if (OP_SEARCH.equalsIgnoreCase(op) || OP_NEW.equalsIgnoreCase(op)
	                    || OP_NEXT.equalsIgnoreCase(op) || OP_PREVIOUS.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op) ) {
	            	
	            	if(OP_SEARCH.equalsIgnoreCase(op)){
	  	    			 
	  	    			 pageNo=1;
	  	    			 
	  	    		 }else if(OP_NEXT.equalsIgnoreCase(op)){
	  	    			 
	  	    			 pageNo++;
	  	    		 }
	  	    		 else if(OP_PREVIOUS.equalsIgnoreCase(op) && pageNo>1){
	  	    			 
	  	    			 pageNo--;
	  	    			 
	  	    		 }
	  	    		 
	  	    		 else if(OP_NEW.equalsIgnoreCase(op)){
	  	    			 
	  	    			 ServletUtility.redirect(ORSView.COURCE_CTL, request, response);
	  	    			 
	  	    			 return;
	  	    		 }
	  	    		 
	  	    		 else if(OP_DELETE.equalsIgnoreCase(op)){
	  	
	  	    			String[] ids = request.getParameterValues("ids");
	  	    		
	  	    			 pageNo=1;
	  	    			 
	  	    			 if(ids!=null&&ids.length>0){
	  	    				 
	  	    				 CourceBean deletebean=new CourceBean();
	  	    				 
	  	    				 for(String id:ids){
	  	    		
	  	    					System.out.println("id="+id);
	  	    					 deletebean.setId(DataUtility.getInt(id));
	  	    					 model.delete(deletebean);
	  	    					 ServletUtility.setSuccessMessage("Record deleted successfully", request);
	  	    				 }
	  	    				 }
	  	    				 else{
	  	    					 ServletUtility.setErrorMessage(
	  	                             "Select at least one record", request);
	  	    				  }
	  	    	
            }}
        	 
        	
        		 
            list = model.search(bean, pageNo, pageSize);
            ServletUtility.setBean(bean, request);
            System.out.println("list "+list.size());
        	 
            //ServletUtility.setList(list, request);
        	/* }*/
            if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
                ServletUtility.setErrorMessage("No record found ", request);
               // ServletUtility.forward(getView(), request, response);
            }
           
          ServletUtility.setBean(bean, request);	
          
            ServletUtility.setList(list, request);

            ServletUtility.setPageNo(pageNo, request);
            ServletUtility.setPageSize(pageSize, request);
            ServletUtility.forward(getView(), request, response);
        }
         catch (ApplicationException e) {
            log.error(e);
            ServletUtility.handleException(e, request, response);
            return;
        }
        log.debug("CourceListCtl doPost End");
       

			}

	@Override
	protected String getView() {
			return ORSView.COURCE_LIST_VIEW;

}}
