package com.taojia.app.Utils;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Component;
@Component
public class SessionFactory {
    public static SqlSessionFactory getSessionFactory(){
        SqlSessionFactory sessionFactory = null;
        String resource= "spring-mybatis.xml";
        try{
        	InputStream is = SessionFactory.class.getClassLoader().getResourceAsStream(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(is);
            return sessionFactory;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
