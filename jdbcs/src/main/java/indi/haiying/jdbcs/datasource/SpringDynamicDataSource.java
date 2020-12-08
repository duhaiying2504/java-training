package indi.haiying.jdbcs.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

@Slf4j
public class SpringDynamicDataSource extends AbstractRoutingDataSource {

    public SpringDynamicDataSource() {
    }

    public SpringDynamicDataSource(Object defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        this.setDefaultTargetDataSource(defaultTargetDataSource);
        this.setTargetDataSources(targetDataSources);
        this.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {

        String name = DataSourceHelper.getName();
        log.warn("the target datasource name: {}", name);
        return name;
    }

}
