package in.co.sunrays.proj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.CourceBean;
import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.CourceModel;
import in.co.sunrays.proj4.model.SubjectModel;

public class SubjectModelTest {

	public static void main(String[] args) throws ParseException, DuplicateRecordException {
		
		         //testAdd();
				//testDelete();
				testUpdate();
				//testFindBySubjectName();
				//testFindByPk();
				//testSearch();
				//testList();
			

	}
	
 public static void testAdd() throws ParseException, DuplicateRecordException {
SubjectModel m=new SubjectModel();
SubjectBean b=new SubjectBean();
	        b.setCourceId(505);
	        b.setCourceName("B.E");
	        b.setSubjectName("EL");
	        b.setDescription("inverters");
	        b.setCreatedBy("root");
			b.setModifiedBy("root");
			b.setCreatedDatetime(new Timestamp(new Date().getTime()));
			b.setModifiedDatetime(new Timestamp(new Date().getTime()));
			try {
				m.add(b);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 }
 
 public static void testDelete(){
	 SubjectModel m=new SubjectModel();
	 SubjectBean b=new SubjectBean();
	
		b.setId(4);
		
		try {
			m.delete(b);
			System.out.println("ho gya");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }

 public static void testUpdate(){
		
	 SubjectModel m=new SubjectModel();
	 SubjectBean b=new SubjectBean();
	   b.setId(4);
	   b.setCourceId(505);
       b.setCourceName("B.E");
       b.setSubjectName("EMT");
       b.setDescription("transient waves");
       b.setCreatedBy("root");
		b.setModifiedBy("root");
		b.setCreatedDatetime(new Timestamp(new Date().getTime()));
		b.setModifiedDatetime(new Timestamp(new Date().getTime()));
	
	try {
		m.update(b);
		System.out.println("HO GYA");
	} catch (ApplicationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (DuplicateRecordException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	


}
 
 public static void testFindByPk(){
		
	 SubjectModel m=new SubjectModel();
	 SubjectBean b=new SubjectBean();
		long pk=3;
		try {
			b=m.findByPk(pk);
			 if (b == null) {
	                System.out.println("Test Find By PK fail");
	            }
	            System.out.println(b.getId());
	            System.out.println(b.getCourceId());
	            System.out.println(b.getSubjectName());
	       
			
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
public static void testFindBySubjectName(){
	
	SubjectModel m=new SubjectModel();
	 SubjectBean b=new SubjectBean();
	try {
		b=m.findBySubjectName("EL");
		
		 if (b == null) {
          System.out.println("Test Find By PK fail");
      }
      System.out.println(b.getId());
      System.out.println(b.getCourceId());
      System.out.println(b.getSubjectName());
 
	} catch (ApplicationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}	

public static void testSearch(){
	 SubjectModel m=new SubjectModel();
	 SubjectBean b=new SubjectBean();

	List list=new ArrayList();
	
	//b.setSubjectName("NETWORK ANALYSIS");
	
	try {
		System.out.println("in hua");
		list=m.search(b, 1, 0);
		System.out.println("out hua");
		
		if(list.size()<0){
			System.out.println(" test Search fails");
		}
		
		Iterator it=list.iterator();
		while(it.hasNext()){
			
			b=(SubjectBean)it.next();
			
			System.out.println(b.getCourceId());
			System.out.println(b.getCourceName());
			System.out.println(b.getDescription());
			System.out.println(b.getCreatedBy());
			System.out.println(b.getModifiedBy());
			System.out.println(b.getCreatedDatetime());
			System.out.println(b.getModifiedDatetime());
		}
	} catch (ApplicationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
public static void testList(){
	
	 SubjectModel m=new SubjectModel();
	 SubjectBean b=new SubjectBean();

	List list=new ArrayList();
	
	try {
		list=m.list(1, 10);
		if(list.size()<0){
			
			System.out.println("test list fails");
		}
		
		Iterator it=list.iterator();
		while(it.hasNext()){
			
			b=(SubjectBean)it.next();
			
			System.out.println(b.getCourceId());
			System.out.println(b.getCourceName());
			System.out.println(b.getDescription());
			System.out.println(b.getCreatedBy());
			System.out.println(b.getModifiedBy());
			System.out.println(b.getCreatedDatetime());
			System.out.println(b.getModifiedDatetime());
		
	}
		
	}catch (ApplicationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
 


}
