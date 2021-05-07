package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.sunrays.proj4.bean.CourceBean;
import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.util.JDBCDataSource;
import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class SubjectModel.
 */
public class SubjectModel {
	
	/** The log. */
	private static Logger log = Logger.getLogger(CourceModel.class);	
	
/**
 * Next pk.
 *
 * @return the integer
 * @throws DatabaseException the database exception
 */
public Integer nextPk() throws DatabaseException{
	
	log.debug("Model nextPK Started");
	
	Connection conn=null;
	int pk=0;
	
	try {
		conn=JDBCDataSource.getConnection();
		
	PreparedStatement p=conn.prepareStatement("SELECT MAX(ID) FROM ST_SUBJECT");
	
	ResultSet rs=p.executeQuery();
	 
	while(rs.next()){
		
		pk=rs.getInt(1);
	}
	rs.close();
	} catch (Exception e) {
		 log.error("Database Exception..", e);
         throw new DatabaseException("Exception : Exception in getting PK");
  }
	finally {
        JDBCDataSource.closeConnection(conn);
    }
    log.debug("Model nextPK End");
   
	return pk+1;
                                                                                     }	
	
/**
 * Adds the.
 *
 * @param bean the bean
 * @return the long
 * @throws DatabaseException the database exception
 * @throws ApplicationException the application exception
 * @throws DuplicateRecordException the duplicate record exception
 */
public long add(SubjectBean bean) throws DatabaseException, ApplicationException, DuplicateRecordException{
	
	log.debug("Model nextPK Started");
	
	  CourceModel model=new CourceModel();
      CourceBean cbean=model.findByPk(bean.getCourceId());
      //SubjectModel model1=new SubjectModel();
     //SubjectBean sbean=model1.findByPk(bean.getsubjectId());//subject name and cource name yaha se set hoge
      
     bean.setCourceName(cbean.getName());
   // bean.setSubjectName(sbean.getSubjectName());

     SubjectBean duplisub=findBySubjectName(bean.getSubjectName());
     
     if(duplisub!=null){
    	 throw new DuplicateRecordException("subject name already exists");
    	 
     }
	
	Connection conn=null;
	
	int pk=0;
	
	pk=nextPk();
	
	try {
		conn=JDBCDataSource.getConnection();
		
		conn.setAutoCommit(false);
		
		PreparedStatement p=conn.prepareStatement("INSERT INTO ST_SUBJECT VALUES (?,?,?,?,?,?,?,?,?,?)");
		
		p.setLong(1,pk);
		p.setLong(2,bean.getCourceId());
		p.setString(3,bean.getCourceName());
		p.setLong(4, bean.getsubjectId());
		p.setString(5,bean.getSubjectName());
		p.setString(6,bean.getDescription());
		p.setString(7, bean.getCreatedBy());
		p.setString(8,bean.getModifiedBy());
		p.setTimestamp(9,bean.getCreatedDatetime());
		p.setTimestamp(10,bean.getModifiedDatetime());
        p.executeUpdate();
		conn.commit();
		p.close();
			
	} catch (Exception e) {
		// TODO Auto-generated catch block
		try {
			conn.rollback();
		} catch (Exception e1) {
			 throw new ApplicationException(
		                "Exception : add rollback exception " + e1.getMessage());
			 }
		e.printStackTrace();
	    throw new ApplicationException(
	            "Exception : Exception in add SUBJECT");	 
	}
	 finally {
	     JDBCDataSource.closeConnection(conn);
	 }
	 log.debug("Model add End");
	 return pk;
}

/**
 * Delete.
 *
 * @param bean the bean
 * @throws ApplicationException the application exception
 */
public void delete(SubjectBean bean) throws ApplicationException {
    log.debug("Model delete Started");
    Connection conn = null;
    try {
        conn = JDBCDataSource.getConnection();
        conn.setAutoCommit(false); // Begin transaction
        PreparedStatement pstmt = conn
                .prepareStatement("DELETE FROM ST_SUBJECT WHERE ID=?");
        pstmt.setLong(1, bean.getId());
       
        pstmt.executeUpdate();
       
        conn.commit(); // End transaction
       
        pstmt.close();
       

    } catch (Exception e) {
        log.error("Database Exception..", e);
        try {
            conn.rollback();
        } catch (Exception ex) {
            throw new ApplicationException(
                    "Exception : Delete rollback exception "
                            + ex.getMessage());
        }
        throw new ApplicationException(
                "Exception : Exception in delete SUBJECT ");
    } finally {
        JDBCDataSource.closeConnection(conn);
    }
    log.debug("Model delete Started");
}

/**
 * Update.
 *
 * @param bean the bean
 * @throws ApplicationException the application exception
 * @throws DuplicateRecordException the duplicate record exception
 */
public void update (SubjectBean bean) throws ApplicationException,DuplicateRecordException{
	
	log.debug("Model update Started");
	
	Connection conn = null;
	
	try {
		conn=JDBCDataSource.getConnection();
		System.out.println("coon par");
		conn.setAutoCommit(false);
		
		PreparedStatement p=conn.prepareStatement("UPDATE ST_SUBJECT SET COURCE_ID=?,COURCE_NAME=?,SUBJECT_ID=?,SUBJECT_NAME=?,DESCRIPTION=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
		System.out.println("preap par");
		p.setLong(1,bean.getCourceId());
		p.setString(2,bean.getCourceName());
		p.setLong(3,bean.getsubjectId());
		p.setString(4,bean.getSubjectName());
		p.setString(5,bean.getDescription());
		p.setString(6, bean.getCreatedBy());
		p.setString(7,bean.getModifiedBy());
		p.setTimestamp(8,bean.getCreatedDatetime());
		p.setTimestamp(9,bean.getModifiedDatetime());
		p.setLong(10,bean.getId());
		System.out.println("p par");
		int i=p.executeUpdate();
		System.out.println("exec par  "+i);
		conn.commit();
		p.close();
		}
	 catch (Exception e) {
		 //e.printStackTrace();
		 
		 log.error("Database Exception..", e);
         try {
             conn.rollback();
         } catch (Exception ex) {
        	 
            throw new ApplicationException(
                     "Exception : Delete rollback exception "
                             + ex.getMessage());
         }
         throw new ApplicationException("Exception in updating subject ");
        
	}
	finally {
        JDBCDataSource.closeConnection(conn);
    }
    log.debug("Model update End");

}	

/**
 * Find by subject name.
 *
 * @param Name the name
 * @return the subject bean
 * @throws ApplicationException the application exception
 */
public SubjectBean findBySubjectName(String Name) throws ApplicationException{
	
	log.debug("Model findBy Email Started");
	
	 SubjectBean bean = null;
     Connection conn = null;
     
     try {
		conn=JDBCDataSource.getConnection();
		
	PreparedStatement p=conn.prepareStatement("SELECT * FROM ST_SUBJECT WHERE SUBJECT_NAME=?");
	
p.setString(1,Name);

ResultSet rs=p.executeQuery();

while(rs.next()){
	
	bean=new SubjectBean();
	
	bean.setId(rs.getLong(1));
	bean.setCourceId(rs.getLong(2));
	bean.setCourceName(rs.getString(3));
	bean.setsubjectId(rs.getLong(4));
	bean.setSubjectName(rs.getString(5));
	bean.setDescription(rs.getString(6));
	bean.setCreatedBy(rs.getString(7));
	bean.setModifiedBy(rs.getString(8));
	bean.setCreatedDatetime(rs.getTimestamp(9));
	bean.setModifiedDatetime(rs.getTimestamp(10));

}
rs.close();
}catch (Exception e) {
	// TODO Auto-generated catch block
	 log.error("Database Exception..", e);
    throw new ApplicationException(
            "Exception : Exception in getting User by Subject name");
    
}
finally {
    JDBCDataSource.closeConnection(conn);
}
log.debug("Model findBy Subject Name End");
return bean;

    }

/**
 * Find by pk.
 *
 * @param pk the pk
 * @return the subject bean
 * @throws ApplicationException the application exception
 */
public SubjectBean findByPk(long pk) throws ApplicationException{
	
	log.debug("Model findBy Email Started");
	
	 SubjectBean bean = null;
     Connection conn = null;
     
     try {
		conn=JDBCDataSource.getConnection();
		
	PreparedStatement p=conn.prepareStatement("SELECT * FROM ST_SUBJECT WHERE ID=?");
	
p.setLong(1,pk);

ResultSet rs=p.executeQuery();

while(rs.next()){
	bean=new SubjectBean();
	bean.setId(rs.getLong(1));
	bean.setCourceId(rs.getLong(2));
	bean.setCourceName(rs.getString(3));
	bean.setsubjectId(rs.getLong(4));
	bean.setSubjectName(rs.getString(5));
	bean.setDescription(rs.getString(6));
	bean.setCreatedBy(rs.getString(7));
	bean.setModifiedBy(rs.getString(8));
	bean.setCreatedDatetime(rs.getTimestamp(9));
	bean.setModifiedDatetime(rs.getTimestamp(10));

	
	}
rs.close();

	} catch (Exception e) {
		// TODO Auto-generated catch block
		 log.error("Database Exception..", e);
         throw new ApplicationException(
                 "Exception : Exception in getting SUBJECT by pk");
         
	}
     finally {
         JDBCDataSource.closeConnection(conn);
     }
     log.debug("Model findBy pk End");
     return bean;
}

/**
 * Search.
 *
 * @param bean the bean
 * @return the list
 * @throws ApplicationException the application exception
 */
public List search(SubjectBean bean) throws ApplicationException {
    return search(bean, 0, 0);
}


/**
 * Search.
 *
 * @param bean the bean
 * @param pageNo the page no
 * @param pageSize the page size
 * @return the list
 * @throws ApplicationException the application exception
 */
public List search(SubjectBean bean, int pageNo, int pageSize)
        throws ApplicationException {
    log.debug("Model search Started");
    StringBuffer sql = new StringBuffer("SELECT * FROM ST_SUBJECT WHERE 1=1");
System.out.println("chalu hua");


    if (bean != null) {
        if (bean.getId() > 0) {
            sql.append(" AND ID = " + bean.getId());
        }
        
        if (bean.getCourceId()>0) {
            sql.append(" AND COURCE_ID = " + bean.getCourceId());
        }
        
     /*   if (bean.getCourceName() != null && bean.getCourceName().length() > 0) {
            sql.append(" AND COURCE_NAME like '" + bean.getCourceName() + "'");
        }
        
        if (bean.getsubjectId()>0) {
        	System.out.println("subject id="+bean.getsubjectId());
            sql.append(" AND SUBJECT_ID = " + bean.getsubjectId() + "");
        }
       
        if (bean.getSubjectName() != null && bean.getSubjectName().length() > 0) {
        	System.out.println("subject id="+bean.getSubjectName());
            sql.append(" AND SUBJECT_NAME like '" + bean.getSubjectName() + "%'");
        }
       
        
        if (bean.getDescription() != null && bean.getDescription().length() > 0) {
            sql.append(" AND DESCRIPTION like '" + bean.getDescription()+ "%'");
        }
*/    }
    

    // if page size is greater than zero then apply pagination
    if (pageSize > 0) {
        // Calculate start record index
        pageNo = (pageNo - 1) * pageSize;

        sql.append(" Limit " + pageNo + " ," + pageSize);
        // sql.append(" limit " + pageNo + "," + pageSize);
    }
System.out.println("if se bahar aaya    "+sql);
    ArrayList list = new ArrayList();
    Connection conn = null;
    System.out.println("conn ke par hua");
    try {
        conn = JDBCDataSource.getConnection();
        System.out.println("connection create hua");
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        System.out.println("preap se bahar hua");
        ResultSet rs = pstmt.executeQuery();
        
System.out.println("result set ke bahar aaya");
        while(rs.next()){
        	bean = new SubjectBean();
        	
        	bean=new SubjectBean();
        	bean.setId(rs.getLong(1));
        	bean.setCourceId(rs.getLong(2));
        	bean.setCourceName(rs.getString(3));
        	bean.setsubjectId(rs.getLong(4));
        	bean.setSubjectName(rs.getString(5));
        	bean.setDescription(rs.getString(6));
        	bean.setCreatedBy(rs.getString(7));
        	bean.setModifiedBy(rs.getString(8));
        	bean.setCreatedDatetime(rs.getTimestamp(9));
        	bean.setModifiedDatetime(rs.getTimestamp(10));

 	
        	
        list.add(bean);
        System.out.println("khatam hua");
         }
        rs.close();
        }
    catch (Exception e) {
        log.error("Database Exception..", e);
        throw new ApplicationException(
                "Exception : Exception in search Cource");
    } finally {
        JDBCDataSource.closeConnection(conn);
    }

    log.debug("Model search End");
    return list;

    
}

/**
 * List.
 *
 * @param pageNo the page no
 * @param pageSize the page size
 * @return the list
 * @throws ApplicationException the application exception
 */
public static List list(int pageNo, int pageSize) throws ApplicationException {
    log.debug("Model list Started");
    ArrayList list = new ArrayList();
    StringBuffer sql = new StringBuffer("select * from ST_SUBJECT");
    // if page size is greater than zero then apply pagination
    System.out.println("in hua");
    if (pageSize > 0) {
        // Calculate start record index
        pageNo = (pageNo - 1) * pageSize;
        sql.append(" limit " + pageNo + "," + pageSize);
    }
System.out.println(sql);
    Connection conn = null;
    System.out.println("if se bahar hua");

    try {
        conn = JDBCDataSource.getConnection();
        System.out.println("conn creat hua");
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        System.out.println("preapred ");
        ResultSet rs = pstmt.executeQuery();
        System.out.println("resultset");
while(rs.next()){
	
	
	SubjectBean bean=new SubjectBean();
	bean.setId(rs.getLong(1));
	bean.setCourceId(rs.getLong(2));
	bean.setCourceName(rs.getString(3));
	bean.setsubjectId(rs.getLong(4));
	bean.setSubjectName(rs.getString(5));
	bean.setDescription(rs.getString(6));
	bean.setCreatedBy(rs.getString(7));
	bean.setModifiedBy(rs.getString(8));
	bean.setCreatedDatetime(rs.getTimestamp(9));
	bean.setModifiedDatetime(rs.getTimestamp(10));


	    list.add(bean);
   }
rs.close();

}
    catch (Exception e) {
        log.error("Database Exception..", e);
        throw new ApplicationException(
                "Exception : Exception in getting list of Cource");
    } finally {
        JDBCDataSource.closeConnection(conn);
    }

    log.debug("Model list End");
    return list;

}

/**
 * List.
 *
 * @return the list
 * @throws ApplicationException the application exception
 */
public static List list() throws ApplicationException {
    return list(0, 0);
}


}