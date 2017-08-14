package com.github.ethanpang.config.datasource;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;


@Aspect
@Component
public class DataSourceAspect {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

//	@Pointcut("@annotation(cn.jclive.cpss.config.datasource.DataSourceType)")
//	public void aspect() {
//	}

    @Before("@annotation(com.github.ethanpang.config.datasource.DataSourceType)")
    public void beforeSwitchDS(JoinPoint jp) throws Throwable {
        Class<?> className = jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();
        Class[] argClass = ((MethodSignature) jp.getSignature()).getParameterTypes();
        String dataSource = DataSourceContextHolder.DAFAULT_DATA_SOURCE;
        try {
            Method method = className.getMethod(methodName, argClass);
            if (method.isAnnotationPresent(DataSourceType.class)) {
                DataSourceType annotation = method.getAnnotation(DataSourceType.class);
                dataSource = annotation.value();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataSourceContextHolder.setDataSourceType(dataSource);
    }

    @After("@annotation(com.github.ethanpang.config.datasource.DataSourceType)")
    public void restoreDataSource(JoinPoint jp) {
        DataSourceContextHolder.clearDataSource();
    }
}
