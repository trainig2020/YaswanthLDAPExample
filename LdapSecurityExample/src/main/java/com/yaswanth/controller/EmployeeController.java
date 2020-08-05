package com.yaswanth.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yaswanth.dto.Department;
import com.yaswanth.dto.Employee;
import com.yaswanth.service.EmployeeService;

@Controller
public class EmployeeController {
    
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value = "/emplist")
	public ModelAndView getAllEmployees(HttpServletRequest request) {
		int deptid =Integer.parseInt(request.getParameter("DeptID"));
		List<Employee> lstemp = employeeService.getAllEmployees(deptid);
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("EmpList", lstemp);
		List<Department> lstdept1 = (List<Department>) httpSession.getAttribute("DeptList");
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("DeptListemp", lstdept1);
		modelAndView.addObject("EmpList", lstemp);
		modelAndView.addObject("homepage", "emppage");
		modelAndView.addObject("name", "names");		
		return modelAndView;
		}
	
	@RequestMapping(value = "/newEmployee", method = RequestMethod.GET)
	public ModelAndView newContact(HttpServletRequest request) {
		String Register  = "NewForm";
		HttpSession session1 = request.getSession();
		List<Employee> lst =(List<Employee>)session1.getAttribute("EmpList");
		ModelAndView model = new ModelAndView("home");
		model.addObject("EmpList", lst);
		model.addObject("Register", Register);
		model.addObject("insertEmployee", "newemployee");
		model.addObject("homepage", "emppage");		
		return model;	
	}

	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	public ModelAndView saveEmployee(@ModelAttribute Employee employee,HttpServletRequest request) {
		int employeeId = Integer.parseInt(request.getParameter("EmpID"));
		int did =  Integer.parseInt(request.getParameter("DID"));
		String empname = request.getParameter("EmpName");
		employeeService.insertEmployee(employee);	
		return new ModelAndView("redirect:/emplist?DeptID="+did);		
	}

	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(HttpServletRequest request) {
		int employeeId = Integer.parseInt(request.getParameter("id"));
		int did =  Integer.parseInt(request.getParameter("did"));
		employeeService.deleteEmployee(employeeId,did);
		return new ModelAndView("redirect:/emplist?DeptID="+did);	
	}

	@RequestMapping(value = "/getEmployee", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		int employeeId = Integer.parseInt(request.getParameter("id"));
		int did =  Integer.parseInt(request.getParameter("did"));
		HttpSession session2 = request.getSession();
		List<Employee> lst =(List<Employee>) session2.getAttribute("EmpList");
		session2.setAttribute("EmpList", lst);
		ModelAndView model = new ModelAndView("home");
		model.addObject("homepage", "emppage");
		model.addObject("EmpList", lst);
		model.addObject("employeeid", employeeId);
		model.addObject("Did", did);
		return model;
	}
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
	public ModelAndView updateEmployee(HttpServletRequest request) {
		int employeeId = Integer.parseInt(request.getParameter("EmpID"));
		int did =  Integer.parseInt(request.getParameter("DID"));
		String empname = request.getParameter("EmpName");
		employeeService.updateEmployee(employeeId,did,empname);
		return new ModelAndView("redirect:/emplist?DeptID="+did);
	}
}

