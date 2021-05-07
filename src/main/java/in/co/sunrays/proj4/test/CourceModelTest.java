package in.co.sunrays.proj4.test;
 
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.CourceBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.CourceModel;

public class CourceModelTest {

	
	
	public static void main(String[] args) throws ParseException {
		testAdd();
		//testDelete();
		//testUpdate();
		//testFindByName();
		//testFindByPk();
		//testSearch();
		//testList();
	

	}
	
	 public static void testAdd() throws ParseException {
		 
		 CourceModel m=new CourceModel();
			CourceBean b=new CourceBean();
			b.setName("M");
			b.setDuration(2);
			b.setCreatedBy("root");
			b.setModifiedBy("root");
			b.setCreatedDatetime(new Timestamp(new Date().getTime()));
			b.setModifiedDatetime(new Timestamp(new Date().getTime()));
			try {
				m.add(b);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DuplicateRecordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 }

	public static void testDelete(){
		 CourceModel m=new CourceModel();
			CourceBean b=new CourceBean();
			
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
		
		 CourceModel m=new CourceModel();
			CourceBean b=new CourceBean();
		b.setId(4);
		b.setName("MCA");
		b.setDuration(2);
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
		
		CourceModel m=new CourceModel();
		CourceBean b=new CourceBean();
	
		long pk=4;
		try {
			b=m.findByPk(pk);
			 if (b == null) {
	                System.out.println("Test Find By PK fail");
	            }
	            System.out.println(b.getId());
	            System.out.println(b.getName());
	            System.out.println(b.getDuration());
	       
			
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
public static void testFindByName(){
	
	CourceModel m=new CourceModel();
	CourceBean b=new CourceBean();
	
	try {
		b=m.findByName("BCA");
		
		 if (b == null) {
             System.out.println("Test Find By PK fail");
         }
         System.out.println(b.getId());
         System.out.println(b.getName());
         System.out.println(b.getDuration());
    
	} catch (ApplicationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}	

public static void testSearch(){
	CourceModel m=new CourceModel();
	CourceBean b=new CourceBean();
	
	List list=new ArrayList();
	
	b.setName("BCA");
	
	try {
		System.out.println("in hua");
		list=m.search(b, 1, 0);
		System.out.println("out hua");
		
		if(list.size()<0){
			System.out.println(" test Search fails");
		}
		
		Iterator it=list.iterator();
		while(it.hasNext()){
			
			b=(CourceBean)it.next();
			
			System.out.println(b.getId());
			System.out.println(b.getName());
			System.out.println(b.getDuration());
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
	
	CourceModel m=new CourceModel();
	CourceBean b=new CourceBean();
	
	List list=new ArrayList();
	
	try {
		list=m.list(1, 10);
		if(list.size()<0){
			
			System.out.println("test list fails");
		}
		
		Iterator it=list.iterator();
		while(it.hasNext()){
			
			b=(CourceBean)it.next();
			
			System.out.println(b.getId());
			System.out.println(b.getName());
			System.out.println(b.getDuration());
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

