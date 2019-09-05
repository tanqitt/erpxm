package com.accp.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.accp.realm.MyRealm;

@Configuration
public class ShiroConfig {
	// 配置核心安全事务管理器
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager securityManager(@Qualifier("myrealm") MyRealm myrealm) {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(myrealm);
		return manager;
	}

	// 配置自定义的权限登录器
	@Bean(name = "myrealm")
	public MyRealm authRealm() {
		MyRealm myShiroRealm = new MyRealm();
		return myShiroRealm;
	}

	// Filter工厂，设置对应的过滤条件和跳转条件
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		/**
		 * anon:所有url都都可以匿名访问; authc: 需要认证才能进行访问; user:配置记住我或认证通过可以访问；
		 */
		// 拦截器
		Map<String, String> map = new HashMap<String, String>();
		map.put("/authc/**", "anon");
		map.put("/index/**", "anon");
		map.put("/system/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		return shiroFilterFactoryBean;
	}

	// 加入注解的使用，不加入这个注解不生效
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

}
