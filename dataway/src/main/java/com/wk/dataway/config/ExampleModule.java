package com.wk.dataway.config;

import lombok.extern.slf4j.Slf4j;
import net.hasor.core.ApiBinder;
import net.hasor.core.DimModule;
import net.hasor.dataql.DimUdfSource;
import net.hasor.dataql.QueryApiBinder;
import net.hasor.dataql.fx.db.runsql.SqlFragment;
import net.hasor.db.JdbcModule;
import net.hasor.db.Level;
import net.hasor.spring.SpringModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


@DimModule
@Component
@Slf4j
public class ExampleModule implements SpringModule {
    @Autowired
    private DataSource dataSource = null;

    @Override
    public void loadModule(ApiBinder apiBinder) throws Throwable {
        log.info("开始加载模块...");
        // .DataSource form Spring boot into Hasor
        apiBinder.installModule(new JdbcModule(Level.Full, this.dataSource));
        // .custom DataQL
        apiBinder.tryCast(QueryApiBinder.class).loadUdfSource(apiBinder.findClass(DimUdfSource.class));
        apiBinder.tryCast(QueryApiBinder.class).bindFragment("sql", SqlFragment.class);
        log.info("加载模块完毕...");
    }
}