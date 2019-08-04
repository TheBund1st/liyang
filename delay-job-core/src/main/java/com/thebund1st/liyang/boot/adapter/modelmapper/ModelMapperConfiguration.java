package com.thebund1st.liyang.boot.adapter.modelmapper;

import com.thebund1st.liyang.adapter.http.rest.resource.DelayJobResource;
import com.thebund1st.liyang.domain.model.DelayJob;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    @Bean(name = "liyang.adapter.modelmapper.ModelMapper")
    protected ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(DelayJob.class, DelayJobResource.class)
                .addMapping(DelayJob::getId, DelayJobResource::setIdentifier);
        return modelMapper;
    }

}
