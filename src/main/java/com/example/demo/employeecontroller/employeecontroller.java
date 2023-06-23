package com.example.demo.employeecontroller;
import com.example.demo.employeemodel.employee;
import com.example.demo.employeeservice.employeeservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/employee")
public class
employeecontroller {
    @Autowired
    employeeservice employeeService;
    @GetMapping("/display")
    public List<employee> display(){
        return employeeService.display();
    }
    @PostMapping("/addemployee")
    public employee createEmployee(@RequestBody employee emp){
        return employeeService.createEmployee(emp);
    }
    @PostMapping("/addallemployees")
    public List<employee> createEmployeeList(@RequestBody List<employee> employees)
    {
        return employeeService.createEmployeeList(employees);
    }

    @GetMapping("/{field}")
    @Cacheable(key = "#field",value = "Employee")
    public List<employee> displaysort(@PathVariable String field){
        return employeeService.displaysort(field);
    }
    @GetMapping("/{pagesize}/{offset}")
    public Page<employee> pagination(@PathVariable int pagesize, @PathVariable int offset){
        return employeeService.pagination(pagesize,offset);

    }

    @DeleteMapping("/delete/{EmpuId}")
    public void deleteEmployeeId(@PathVariable int EmpuId){
        employeeService.deleteEmployee(EmpuId);
    }
    @PutMapping("/empu")
    public employee updateEmployee(@RequestBody employee emp){
        return employeeService.updateEmployees(emp);
    }

}
