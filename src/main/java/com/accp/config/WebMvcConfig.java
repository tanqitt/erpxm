package com.accp.config;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}

	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		return new StringHttpMessageConverter(StandardCharsets.UTF_8);
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		return new MappingJackson2HttpMessageConverter();
	}
	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
	}
	
	@Override
	protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(stringHttpMessageConverter());
		converters.add(mappingJackson2HttpMessageConverter());
		converters.add(new ByteArrayHttpMessageConverter());
		super.configureMessageConverters(converters);
	}

	@Override
	protected void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*");
		super.addCorsMappings(registry);
	}
}
