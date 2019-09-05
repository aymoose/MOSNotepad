package com.mos;

import com.mos.db.DBManager;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-23
 * Time:12:38
 */
public class TestDBManager {
    @Test
    public void testConnection(){
        Connection connection = DBManager.getConnection();
        System.out.println(connection);
        Assert.assertNotNull(connection);

    }
}
