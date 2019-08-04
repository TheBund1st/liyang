package com.thebund1st.liyang.adapter.http.rest.assembler;

import com.thebund1st.liyang.adapter.http.rest.resource.DelayJobResource;
import com.thebund1st.liyang.domain.model.DelayJob;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public class DelayJobResourceAssembler {

    private final ModelMapper modelMapper;

    public DelayJobResource toResource(DelayJob model) {
        return modelMapper.map(model, DelayJobResource.class);
    }
}
