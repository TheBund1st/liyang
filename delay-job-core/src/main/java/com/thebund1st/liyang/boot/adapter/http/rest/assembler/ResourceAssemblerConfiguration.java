package com.thebund1st.liyang.boot.adapter.http.rest.assembler;

import com.thebund1st.liyang.adapter.http.rest.assembler.DelayJobResourceAssembler;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class ResourceAssemblerConfiguration {

    @Resource(name = "liyang.adapter.modelmapper.ModelMapper")
    private ModelMapper modelMapper;

    @Bean
    public DelayJobResourceAssembler delayJobResourceAssembler() {
        return new DelayJobResourceAssembler(modelMapper);
    }

}
