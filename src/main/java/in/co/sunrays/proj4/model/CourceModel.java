package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.CollegeBean;
import in.co.sunrays.proj4.bean.CourceBean;
import in.co.sunrays.proj4.bean.StudentBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.util.JDBCDataSource;

// TODO: Auto-generated Javadoc
/**
 * The Class CourceModel.
 */
public class CourceModel {
	
/** The log. */
private static Logger log = Logger.getLogger(CourceModel.class);

/**
 * Next PK.
 *
 * @return the integer
 * @throws DatabaseException the database exception
 */
public Integer nextPK() throws DatabaseException {
        log.debug("Model nextPK Started");
        Connection conn = null;
        int pk = 0;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn
                    .prepareStatement("SELECT MAX(ID) FROM ST_COURCE");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                pk = rs.getInt(1);
            }
            rs.close();

        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new DatabaseException("Exception : Exception in getting PK");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model nextPK End");
        return pk + 1;
    }
	
	
/**
 * Adds the.
 *
 * @param bean the bean
 * @return the long
 * @throws ApplicationException the application exception
 * @throws DuplicateRecordException the duplicate record exception
 * @throws DatabaseException the database exception
 */
public long add(CourceBean bean) throws ApplicationException,
     DuplicateRecordException, DatabaseException {
	
	CourceModel model=new CourceModel();
    CourceBean cbean=model.findByPk(bean.getCourceId());
   // bean.setName(cbean.getName());

    CourceBean duplicatecource=findByName(bean.getName());
    
    if(duplicatecource!=null){
    	throw new DuplicateRecordException("cource allready exists");
    	
    	
    }
    
    
 log.debug("Model add Started");
 Connection conn=null;
 int pk=0;
 pk = nextPK();
 
 try {
	 
	 conn=JDBCDataSource.getConnection();
	 conn.setAutoCommit(false);
	PreparedStatement p=conn.prepareStatement("INSERT INTO ST_COURCE VALUES (?,?,?,?,?,?,?,?)");
	
	p.setLong(1,pk);
	p.setString(2,bean.getName());
	p.setLong(3,bean.getCourceId());
	p.setInt(4,bean.getDuration());
	p.setString(5,bean.getCreatedBy());
	p.setString(6,bean.getModifiedBy());
	p.setTimestamp(7,bean.getCreatedDatetime());
	p.setTimestamp(8,bean.getModifiedDatetime());
	
	p.executeUpdate();
	System.out.println("ho gya");
	
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
            "Exception : Exception in add Cource");
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
public void delete(CourceBean bean) throws ApplicationException {
    log.debug("Model delete Started");
    Connection conn = null;
    try {
        conn = JDBCDataSource.getConnection();
        conn.setAutoCommit(false); // Begin transaction
        PreparedStatement pstmt = conn
                .prepareStatement("DELETE FROM ST_COURCE WHERE ID=?");
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
                "Exception : Exception in delete COURCE ");
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
public void update (CourceBean bean) throws ApplicationException,DuplicateRecordException{
	
	log.debug("Model update Started");
	
	Connection conn = null;
	
	try {
		conn=JDBCDataSource.getConnection();
		
		conn.setAutoCommit(false);
		
		PreparedStatement p=conn.prepareStatement("UPDATE ST_COURCE SET NAME=?,COURCE_ID=?,DURATION=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
	
		p.setString(1,bean.getName());
		p.setLong(2,bean.getCourceId());
		p.setLong(3,bean.getDuration());
		p.setString(4,bean.getCreatedBy());
		p.setString(5,bean.getModifiedBy());
		p.setTimestamp(6,bean.getCreatedDatetime());
		p.setTimestamp(7,bean.getModifiedDatetime());
		p.setLong(8,bean.getId());
	
	p.executeUpdate();
	conn.commit();
	p.close();
	
	} catch (Exception e) {
	 
		 log.error("Database Exception..", e);
         try {
             conn.rollback();
         } catch (Exception ex) {
             throw new ApplicationException(
                     "Exception : Delete rollback exception "
                             + ex.getMessage());
         }
         throw new ApplicationException("Exception in updating cource ");
	}
	finally {
        JDBCDataSource.closeConnection(conn);
    }
    log.debug("Model update End");
}

/**
 * Find by name.
 *
 * @param Name the name
 * @return the cource bean
 * @throws ApplicationException the application exception
 */
public CourceBean findByName(String Name) throws ApplicationException{
	
	log.debug("Model findBy Email Started");
	
	 CourceBean bean = null;
     Connection conn = null;
     
     try {
		conn=JDBCDataSource.getConnection();
		
	PreparedStatement p=conn.prepareStatement("SELECT * FROM ST_COURCE WHERE NAME=?");
	
p.setString(1,Name);

ResultSet rs=p.executeQuery();

while(rs.next()){
	bean=new CourceBean();
	
	bean.setId(rs.getLong(1));
	bean.setName(rs.getString(2));
	bean.setCourceId(rs.getLong(3));
	bean.setDuration(rs.getInt(4));
	bean.setCreatedBy(rs.getString(5));
	bean.setModifiedBy(rs.getString(6));
	bean.setCreatedDatetime(rs.getTimestamp(7));
	bean.setModifiedDatetime(rs.getTimestamp(8));
	
	}
rs.close();

	} catch (Exception e) {
		// TODO Auto-generated catch block
		 log.error("Database Exception..", e);
         throw new ApplicationException(
                 "Exception : Exception in getting User by name");
         
	}
     finally {
         JDBCDataSource.closeConnection(conn);
     }
     log.debug("Model findBy Name End");
     return bean;
}

/**
 * Find by pk.
 *
 * @param pk the pk
 * @return the cource bean
 * @throws ApplicationException the application exception
 */
public CourceBean findByPk(long pk) throws ApplicationException{
	
	log.debug("Model findBy Email Started");
	
	 CourceBean bean = null;
     Connection conn = null;
     
     try {
		conn=JDBCDataSource.getConnection();
		
	PreparedStatement p=conn.prepareStatement("SELECT * FROM ST_COURCE WHERE ID=?");
	
p.setLong(1,pk);

ResultSet rs=p.executeQuery();

while(rs.next()){
	bean=new CourceBean();
	
	bean.setId(rs.getLong(1));
	bean.setName(rs.getString(2));
	bean.setCourceId(rs.getLong(3));
	bean.setDuration(rs.getInt(4));
	bean.setCreatedBy(rs.getString(5));
	bean.setModifiedBy(rs.getString(6));
	bean.setCreatedDatetime(rs.getTimestamp(7));
	bean.setModifiedDatetime(rs.getTimestamp(8));
	
	}
rs.close();

	} catch (Exception e) {
		// TODO Auto-generated catch block
		 log.error("Database Exception..", e);
         throw new ApplicationException(
                 "Exception : Exception in getting User by pk");
         
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
public List search(CourceBean bean) throws ApplicationException {
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
public List search(CourceBean bean, int pageNo, int pageSize)
        throws ApplicationException {
    log.debug("Model search Started");
    StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURCE WHERE 1=1");
System.out.println("chalu hua");

if(bean.getCourceId()>0){
CourceModel cmod = new CourceModel();
CourceBean cbe = cmod.findByPk(bean.getCourceId());
bean.setName(cbe.getName());
}

    if (bean != null) {
        if (bean.getId() > 0) {
            sql.append(" AND ID = " + bean.getId());
        }
        
       /* if (bean.getName() != null && bean.getName().length() > 0) {
            sql.append(" AND NAME like '" + bean.getName()
                    + "%'");
        }
        
        if (bean.getCourceId()>0) {
            sql.append(" AND COURCE_ID like '" + bean.getCourceId() + "'");
        }
       
        
        if (bean.getDuration()>0) {
            sql.append(" AND DURATION like '" + bean.getDuration() + "%'");
        }*/
       
    }
    

    // if page size is greater than zero then apply pagination
    if (pageSize > 0) {
        // Calculate start record index
        pageNo = (pageNo - 1) * pageSize;

        sql.append(" Limit " + pageNo + ", " + pageSize);
        // sql.append(" limit " + pageNo + "," + pageSize);
    }
System.out.println("query====   "+sql);
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
        	bean = new CourceBean();	
        bean.setId(rs.getLong(1));
        bean.setName(rs.getString(2));
        bean.setCourceId(rs.getLong(3));
        bean.setDuration(rs.getInt(4));
        bean.setCreatedBy(rs.getString(5));
        bean.setModifiedBy(rs.getString(6));
        bean.setCreatedDatetime(rs.getTimestamp(7));
        bean.setModifiedDatetime(rs.getTimestamp(8));
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
    StringBuffer sql = new StringBuffer("select * from ST_COURCE");
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
	
	
	CourceBean bean=new CourceBean();
	
	bean.setId(rs.getLong(1));
    bean.setName(rs.getString(2));
    bean.setCourceId(rs.getLong(3));
    bean.setDuration(rs.getInt(4));
    bean.setCreatedBy(rs.getString(5));
    bean.setModifiedBy(rs.getString(6));
    bean.setCreatedDatetime(rs.getTimestamp(7));
    bean.setModifiedDatetime(rs.getTimestamp(8));
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
