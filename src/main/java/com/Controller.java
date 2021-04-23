package com;

import com.Entity.Employe;
import com.Repository.EmployeeRepository;
import com.Service.ReporteService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@SpringBootApplication
@RestController
public class Controller {
    @Autowired
    EmployeeRepository employeRepository;
    @Autowired
    ReporteService reporteService;
    @GetMapping("/all")
    public List<Employe> Get(){
/*        Employe employe = new Employe();
        employe.setDesignation("des");
        employe.setName("name");
        employe.setSalary(1.0);
        employe.setDoj("doj");*/
//        employeRepository.save(employe);
        List<Employe> employeList = employeRepository.findAll();
        return employeList;
    }
    @GetMapping("/report/{format}")
    public String GenerateReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return reporteService.exportReport(format);
    }

    public static void main(String[] args) {
        SpringApplication.run(Controller.class);
    }
}
