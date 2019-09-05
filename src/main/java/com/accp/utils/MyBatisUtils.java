package com.accp.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtils {
	// ����mybatis�Ự�����Ķ���
	static SqlSessionFactoryBuilder builder = null;

	static SqlSessionFactory factory = null;
	static {
		builder = new SqlSessionFactoryBuilder();
		try {
			InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
			// ����mybatis��������
			factory = builder.build(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static SqlSession getSession() {
		// �����Ự����
		SqlSession session = factory.openSession();
		return session;
	}

}
