package com.storeprocedure.dto;

import com.storeprocedure.utils.CollectionUtils;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class EmployeeDto {

    private Long id;

    @NotBlank
    private String name;


    public static List<EmployeeDto> toList(Map<String, Object> out) {
        List<EmployeeDto> dtos = new ArrayList<>();
        List<Map<String, Object>> lists = (List<Map<String, Object>>) out.get("#result-set-1");
        CollectionUtils.nullSafe(lists).forEach(l -> {
            EmployeeDto dto = new EmployeeDto();
            dto.setId((Long) l.get("id"));
            dto.setName((String) l.get("name"));
            dtos.add(dto);
        });
        return dtos;
    }
}
