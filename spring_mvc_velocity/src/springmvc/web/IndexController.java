package springmvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import springmvc.entity.Student;

/**
 * 2014年4月18日 13:01:06
 */
@Controller
public class IndexController {
	
	@RequestMapping(value = "/")
	public String index_vm(Model model) {
		model.addAttribute("userName", "nick");
		
		Student student = new Student();
		student.setId(3);
		student.setName("NICK");
		
		model.addAttribute("student", student);
		
		return "index";
	}
	
}
