package com.sms.data.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Iterator;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterDisposer;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.StatementCreatorUtils;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.datasource.DataSourceUtils;




public class JdbcTemplateEx extends JdbcTemplate {

	public JdbcTemplateEx() {
		super();
	}

	public JdbcTemplateEx(DataSource dataSource) {
		super(dataSource);
	}

	public void updateMap(Map map, String tableName, String primaryKeyName) throws DataAccessException
    {
        
        Object pkvalue = map.get(primaryKeyName);
        if(pkvalue == null)throw new InvalidDataAccessApiUsageException(
            "~~~~~~Primary Key doesn't exist in Map~~~~~~");
        
        Object[] args = new Object[map.size()];
        
        String updateSql = "update " + tableName + " set ";
        
        Iterator it = map.keySet().iterator();
        int j = 0;
        while(it.hasNext())
        {
            String keyName = (String)it.next();
            if(primaryKeyName.equals(keyName))continue;
            updateSql = updateSql + keyName + " = ?,";
            args[j] = map.get(keyName);
            j++;
        }
        args[map.size() - 1] = pkvalue;
        
        updateSql = updateSql.substring(0, updateSql.length() - 1) + "  where ";
        
        updateSql = updateSql + primaryKeyName + " = ? ";
   
        update(updateSql, args);
   
    }

	public void insertMap(Map map, String tableName) throws DataAccessException
    {
        
        String keyStr = "( ";
        String valueStr = "( ";
        
        Object[] args = new Object[map.size()];
        
        Iterator it = map.keySet().iterator();
        for(int j = 0;it.hasNext();j++)
        {
            String key = (String)it.next();
            keyStr = keyStr + key + ",";
            valueStr = valueStr + "?,";
            args[j] = map.get(key);
        }
        
        keyStr = keyStr.substring(0, keyStr.length() - 1) + " ) ";
        valueStr = valueStr.substring(0, valueStr.length() - 1) + " ) ";
        
        String insertSql = "insert into " + tableName + " " + keyStr + " values " + valueStr;


        update(insertSql, args);
        
    }
	
	
	
    public int update(String sql, final Object[] args) throws DataAccessException
    {    	
        return update(sql, new ArgPreparedStatementSetter(args));
    }
	


private static class ArgPreparedStatementSetter implements PreparedStatementSetter, ParameterDisposer
{

    private final Object[] args;

    public ArgPreparedStatementSetter(Object[] args)
    {
        this.args = args;
    }

    public void setValues(PreparedStatement ps) throws SQLException
    {
        if(this.args == null)return;
        for(int i = 0;i < this.args.length;i++)
        {
        	
            if(args[i] instanceof SqlLobValue)
            {
            	
                StatementCreatorUtils.setParameterValue(ps, i + 1,
                    Types.CLOB, null, this.args[i]);
                
            }
            else
            {
            	
                StatementCreatorUtils.setParameterValue(ps, i + 1,
                    SqlTypeValue.TYPE_UNKNOWN, null, this.args[i]);
                
            }
        }

    }

    public void cleanupParameters()
    {
        StatementCreatorUtils.cleanupParameters(this.args);
    }
}
	
	
	
}
