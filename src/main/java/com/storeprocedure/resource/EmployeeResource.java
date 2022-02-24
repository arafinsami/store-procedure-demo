package com.storeprocedure.resource;

import com.storeprocedure.dto.EmployeeDto;
import com.storeprocedure.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.storeprocedure.exception.ApiError.fieldError;
import static com.storeprocedure.utils.ResponseBuilder.error;
import static com.storeprocedure.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequiredArgsConstructor
@Api(tags = "Employee Data")
@RequestMapping(
        "/api/v1/employee"
)
public class EmployeeResource {

    private final EmployeeService service;

    @PostMapping("/save")
    @ApiOperation(value = "save employee", response = EmployeeDto.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeDto dto,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        service.save(dto);
        return ok(success("employee saved successfully").getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update employee", response = EmployeeDto.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        service.update(dto);
        return ok(success("employee updated successfully").getJson());
    }

    @GetMapping("/list")
    @ApiOperation(value = "get all employees", response = EmployeeDto.class)
    public ResponseEntity<JSONObject> findAll() {

        List<EmployeeDto> dtos = EmployeeDto.toList(service.findAll());
        return ok(success(dtos).getJson());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get employee by id", response = EmployeeDto.class)
    public ResponseEntity<JSONObject> findById(@PathVariable Long id) {

        EmployeeDto dto = service.findById(id);
        return ok(success(dto).getJson());
    }

}
