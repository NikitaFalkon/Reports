package com.Service;

import com.Entity.Employe;
import com.Repository.EmployeeRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReporteService {
    @Autowired
    EmployeeRepository employeRepository;
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        List<Employe> employeList = employeRepository.findAll();
        File file = ResourceUtils.getFile("classpath:employees.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employeList);
        Map<String, Object> map = new HashMap<>();
        map.put("My Name", "is Nikita");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, "C:\\FilesForJava" + "\\employees.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, "C:\\FilesForJava" + "\\employees.pdf");
        }
        return "Done!";
    }
}
