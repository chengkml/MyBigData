package com.ck;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.ck.base.SpringJunitTestBase;

public class SeqTest extends SpringJunitTestBase{
	
	private AtomicInteger errCount = new AtomicInteger(0);

	private static final int threadNums = 2000;

	private static final int poolSize = 1000;

	@Autowired
	private DataSource datasource;

	private CountDownLatch countDown;

	private JdbcTemplate jt;

	@Before
	public void before() {
		jt = new JdbcTemplate(datasource);
		countDown = new CountDownLatch(threadNums);
	}

	@After
	public void after() {
		try {
			countDown.await();
			System.out.println(errCount);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetSeq() {
		List<Map<String, Object>> res = jt.queryForList("select * from dacp_meta_object_seq",new Object[] {});
		assertTrue(!res.isEmpty());
	}

	@Test
	public void testGenerateOneSeq() {
		assertTrue(generateSeq()>0);
	}

	@Test
	public void testMultiThreads() {
		Executor pool = Executors.newFixedThreadPool(poolSize);
		for(int i = 0;i<threadNums;i++) {
			pool.execute(new GenerateSeqThread());
		}
	}

	@Test
	public void testMultiHosts() {
		Executor pool = Executors.newFixedThreadPool(poolSize);
		for(int i = 0;i<threadNums/2;i++) {
			pool.execute(new GenerateSeqThread());
		}
		for(int i = 0;i<threadNums/2;i++) {
			pool.execute(new GenerateSeqThread2());
		}
	}
	
	@Transactional
    synchronized int generateSeq(String metaClassType){
        Integer seqVal = jt.queryForObject("select max(seq_val) from dacp_meta_object_seq where meta_class=?",new String[]{metaClassType},Integer.class);
        int result = (seqVal==null?0:seqVal) + 1;
        try{
            int updateSuccess = jt.update("insert into dacp_meta_object_seq values(?,?)",new Object[]{metaClassType,result});
            if(updateSuccess>0){
                return result;
            }else{
                return generateSeq(metaClassType);
            }

        }catch(DataAccessException e){
            e.printStackTrace();
            return generateSeq(metaClassType);
        }
    }
	
	@Transactional
    synchronized int generateSeq3(String metaClassType)
    {
      Connection conn = null;
      try
      {
        conn = datasource.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement pstat2 = conn.prepareStatement("select max(seq_val) from dacp_meta_object_seq where meta_class=?");
        pstat2.setString(1, metaClassType);
        ResultSet rs = pstat2.executeQuery();
        int result = 0;
        if (rs.next())
        {
          Integer seqVal = Integer.valueOf(rs.getInt(1));
          result = (seqVal == null ? 0 : seqVal.intValue()) + 1;
        }
        rs.close();
        pstat2.close();
        PreparedStatement pstat = conn.prepareStatement("insert into dacp_meta_object_seq values(?,?)");
        pstat.setString(1, metaClassType);
        pstat.setInt(2, result);
        
        int recnt = pstat.executeUpdate();
        if (recnt > 0)
        {
          pstat.close();
          return result;
        }
        pstat.close();
        conn.rollback();
        conn.setAutoCommit(true);
        conn.close();
        return generateSeq3(metaClassType);
      }
      catch (SQLException e)
      {
        e.printStackTrace();
        if (conn != null) {
          try
          {
            conn.rollback();
            conn.setAutoCommit(true);
            conn.close();
          }
          catch (SQLException e1)
          {
            e1.printStackTrace();
          }
        }
        return generateSeq3(metaClassType);
      }
      finally
      {
        if (conn != null) {
          try
          {
            conn.commit();
            conn.setAutoCommit(true);
            conn.close();
          }
          catch (SQLException e)
          {
            e.printStackTrace();
          }
        }
      }
    }
	
	@Transactional
    synchronized int generateSeq4(String metaClassType)
    {
      Connection conn = null;
      try
      {
        conn = datasource.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement pstat2 = conn.prepareStatement("select max(seq_val) from dacp_meta_object_seq where meta_class=?");
        pstat2.setString(1, metaClassType);
        ResultSet rs = pstat2.executeQuery();
        int result = 0;
        if (rs.next())
        {
          Integer seqVal = Integer.valueOf(rs.getInt(1));
          result = (seqVal == null ? 0 : seqVal.intValue()) + 1;
        }
        rs.close();
        pstat2.close();
        PreparedStatement pstat = conn.prepareStatement("insert into dacp_meta_object_seq values(?,?)");
        pstat.setString(1, metaClassType);
        pstat.setInt(2, result);
        
        int recnt = pstat.executeUpdate();
        if (recnt > 0)
        {
          pstat.close();
          return result;
        }
        pstat.close();
        conn.rollback();
        conn.close();
        return generateSeq4(metaClassType);
      }
      catch (SQLException e)
      {
        e.printStackTrace();
        if (conn != null) {
          try
          {
            conn.rollback();
            conn.setAutoCommit(true);
            conn.close();
          }
          catch (SQLException e1)
          {
            e1.printStackTrace();
          }
        }
        return generateSeq4(metaClassType);
      }
      finally
      {
        if (conn != null) {
          try
          {
            conn.commit();
            conn.setAutoCommit(true);
            conn.close();
          }
          catch (SQLException e)
          {
            e.printStackTrace();
          }
        }
      }
    }
	
	@Transactional
	synchronized int generateSeq5(String metaClassType)
	{
		Connection conn = null;
		PreparedStatement pstat = null;
		PreparedStatement pstat2 = null;
		ResultSet rs = null;
		try{
			conn = datasource.getConnection();
			conn.setAutoCommit(false);
			pstat2 = conn.prepareStatement("select max(seq_val) from dacp_meta_object_seq where meta_class=?");
			pstat2.setString(1, metaClassType);
			rs = pstat2.executeQuery();
			int result = 0;
			if (rs.next()){
				Integer seqVal = Integer.valueOf(rs.getInt(1));
				result = (seqVal == null ? 0 : seqVal.intValue()) + 1;
			}
			rs.close();
			pstat2.close();
			pstat = conn.prepareStatement("insert into dacp_meta_object_seq values(?,?)");
			pstat.setString(1, metaClassType);
			pstat.setInt(2, result);
			int recnt = pstat.executeUpdate();
			if (recnt > 0){
				pstat.close();
				return result;
			}
			pstat.close();
			conn.rollback();
			conn.setAutoCommit(true);
			conn.close();
			return generateSeq(metaClassType);
		}catch (SQLException e){
			e.printStackTrace();
			if (conn != null) {
				try{
					conn.rollback();
					conn.setAutoCommit(true);
					conn.close();
				}catch (SQLException e1){
					e1.printStackTrace();
				}
			}
			return generateSeq(metaClassType);
		}finally{
			if(pstat!=null) {
				try {
					pstat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstat2!=null) {
				try {
					pstat2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try{
					conn.commit();
					conn.setAutoCommit(true);
					conn.close();
				}catch (SQLException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	@Transactional
	synchronized int generateSeq6(String metaClassType)
	{
		Connection conn = null;
		PreparedStatement pstat = null;
		PreparedStatement pstat2 = null;
		ResultSet rs = null;
		try{
			conn = datasource.getConnection();
			conn.setAutoCommit(false);
			pstat2 = conn.prepareStatement("select max(seq_val) from dacp_meta_object_seq where meta_class=?");
			pstat2.setString(1, metaClassType);
			rs = pstat2.executeQuery();
			int result = 0;
			if (rs.next()){
				Integer seqVal = Integer.valueOf(rs.getInt(1));
				result = (seqVal == null ? 0 : seqVal.intValue()) + 1;
			}
			rs.close();
			pstat2.close();
			pstat = conn.prepareStatement("insert into dacp_meta_object_seq values(?,?)");
			pstat.setString(1, metaClassType);
			pstat.setInt(2, result);
			int recnt = pstat.executeUpdate();
			if (recnt > 0){
				pstat.close();
				return result;
			}
			pstat.close();
			conn.rollback();
			conn.setAutoCommit(true);
			conn.close();
			return generateSeq(metaClassType);
		}catch (SQLException e){
			e.printStackTrace();
			if (conn != null) {
				try{
					conn.rollback();
					conn.setAutoCommit(true);
					conn.close();
				}catch (SQLException e1){
					e1.printStackTrace();
				}
			}
			return generateSeq(metaClassType);
		}finally{
			if(pstat!=null) {
				try {
					pstat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstat2!=null) {
				try {
					pstat2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try{
					conn.commit();
					conn.setAutoCommit(true);
					conn.close();
				}catch (SQLException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	@Transactional
	synchronized int generateSeq2(String metaClassType){
        Integer seqVal = jt.queryForObject("select max(seq_val) from dacp_meta_object_seq where meta_class=?",new String[]{metaClassType},Integer.class);
        int result = (seqVal==null?0:seqVal) + 1;
        try{
            int updateSuccess = jt.update("insert into dacp_meta_object_seq values(?,?)",new Object[]{metaClassType,result});
            if(updateSuccess>0){
                return result;
            }else{
                return generateSeq2(metaClassType);
            }

        }catch(DataAccessException e){
            e.printStackTrace();
            return generateSeq2(metaClassType);
        }
    }

	@Transactional
	private synchronized int generateSeq() {
		try {
			int seq = jt.queryForObject("select max(seq_val) from dacp_meta_object_seq where meta_class=?", Integer.class,new Object[] {"table"});
			jt.update("insert into dacp_meta_object_seq values (?,?)",new Object[] {"table", seq+1});
			return seq+1;
		}catch(Exception e) {
			e.printStackTrace();
			errCount.addAndGet(1);
			return generateSeq();
		}
	}

	@Transactional
	private synchronized int generateSeq2() {
		try {
			int seq = jt.queryForObject("select max(seq_val) from dacp_meta_object_seq where meta_class=?", Integer.class,new Object[] {"table"});
			jt.update("insert into dacp_meta_object_seq values (?,?)",new Object[] {"table", seq+1});
			return seq+1;
		}catch(Exception e) {
			e.printStackTrace();
			errCount.addAndGet(1);
			return generateSeq2();
		}
	}

	class GenerateSeqThread implements Runnable{

		@Override
		public void run() {
			generateSeq5("table");
			countDown.countDown();
		}

	}

	class GenerateSeqThread2 implements Runnable{

		@Override
		public void run() {
			generateSeq6("table");
			countDown.countDown();
		}

	}
}
