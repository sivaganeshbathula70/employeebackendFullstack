package com.example.demo.employeeservice;

import com.example.demo.employeemodel.employee;
import com.example.demo.employeerepository.employeerepo;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class employeeserviceTest {
    @Autowired
    employeeservice employeeService;
    @MockBean
    employeerepo employeerep;
    private int empuId;
    //  private employee ;


    @Test
    public void testget() {
        when(employeerep.findAll()).thenReturn(Stream.of(new employee(654, "u", "13", "7865"), new employee(4532, "u", "3", "4321")).collect(Collectors.toList()));
        // thenReturn(Stream.of(new employee(654,"u","13","7865"),new employee(4532,"u","3","4321")).collect(Collectors.toList()));
        assertEquals(2, employeeService.display().size());
    }

    @Test
    void testpagination() {
        List<employee> emp = Stream.of(new employee(123, "u", "878", "879")).toList();
        Page<employee> employeePage = new PageImpl<employee>(emp);
        when(employeerep.findAll(any(PageRequest.class))).thenReturn(employeePage);
        assertEquals(1, employeeService.pagination(1, 1).getSize());
    }

    @Test
    public void saveUser() {
        employee empu = new employee(123, "u", "u", "786");
        when(employeerep.save(empu)).thenReturn(empu);
        assertEquals(empu, employeeService.createEmployee(empu));
    }

    //As the method return type is void we can't expect any return and we can't compare with asserts.
    @Test
    public void deleteUser() {
        employee eu = new employee(123, "u", "8987", "897");
        employeeService.deleteEmployee(123);
        verify(employeerep, times(1)).deleteById(123);
    }

    @Test
    public void createemployee() {
        List<employee> eu = new ArrayList<>();
        eu.add(new employee(123, "u", "8987", "897"));
        eu.add(new employee(123, "u", "8987", "897"));

        when(employeerep.saveAll(eu)).thenReturn(eu);
        assertEquals(eu, employeeService.createEmployeeList(eu));
    }

    @Test
    public void sortdata() {
        List<employee> eu = new ArrayList<>();
        eu.add(new employee(123, "u", "8987", "897"));
        eu.add(new employee(123, "u", "8987", "897"));

        when(employeerep.findAll(any(Sort.class))).thenReturn(eu);
        assertEquals(2, employeeService.displaysort("u").size());
    }
   /*
    @Test
    public void updateemployee() {
        employee empu = new employee(123, "u", "u", "786");
         employee eu = new employee(123, "u", "u", "786");
        when(employeerep.findById(eu.getEmpuId()).orElseThrow(null).setName("");
        empu.getEmpuId();
        empu.setName("k");
        empu.setAge("u");
        empu.setSalary("798");
        employee u = employeerep.save(empu);"
        assertNotEquals(eu.getName(), u.getName());


    }
    
    */
}