package test.SpringMVC;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/fcoupon")
public class Hello {
	
	@RequestMapping("/say")
	@ResponseBody
	public String say()
	{
		return "hello world master";
	}

}
