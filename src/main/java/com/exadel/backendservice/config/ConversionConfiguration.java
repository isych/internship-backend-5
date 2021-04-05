package com.exadel.backendservice.config;

import com.exadel.backendservice.util.converter.EventDtoToEventEntityConverter;
import com.exadel.backendservice.util.converter.EventEntityToEventDtoConverter;
import com.exadel.backendservice.util.converter.TechDtoToTechEntityConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ConversionConfiguration {

    @Bean
    public ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();
        converters.add(new EventDtoToEventEntityConverter());
        converters.add(new TechDtoToTechEntityConverter());
        converters.add(new EventEntityToEventDtoConverter());
        bean.setConverters(converters);
        return bean;
    }

}
