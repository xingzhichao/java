package com.xzc.spring.testcompentscan.config;

import com.xzc.spring.testcompentscan.filtertype.TulingFilterType;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by smlz on 2019/5/19.
 */
@Configuration
/*@ComponentScan(basePackages = {"com.tuling.testcompentscan"},excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,value = {Controller.class}),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,value = {TulingService.class})
})*/
@ComponentScan(basePackages = {"com.xzc.spring.testcompentscan"},excludeFilters = {
//        @ComponentScan.Filter(type = FilterType.ANNOTATION,value = {Controller.class, Service.class}),
//        @ComponentScan.Filter(type = FilterType.CUSTOM,value = TulingFilterType.class)
},includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,value = Repository.class)
})
/*@ComponentScan(basePackages = {"com.tuling.testcompentscan"},includeFilters = {
        @ComponentScan.Filter(type = FilterType.CUSTOM,value = TulingFilterType.class)
},useDefaultFilters = false)*/
//@ComponentScan(basePackages ={"com.tuling.testcompentscan"} )
public class MainConfig {
}
