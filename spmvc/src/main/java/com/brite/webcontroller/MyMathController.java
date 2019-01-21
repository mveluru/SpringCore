package com.brite.webcontroller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brite.model.MyMath;

@RestController
public class MyMathController {

	@RequestMapping(value = "/sum", method = RequestMethod.POST)
	public int addition(@RequestBody Map<String, Integer> param) {
		int number1 = param.get("number1");
		int number2 = param.get("number2");
		return number1 + number2;
	}

	@RequestMapping(value = "/sum2", method = RequestMethod.POST)
	public int additionPojo(@RequestBody MyMath math) {
		int number1 = math.getNumber1();
		int number2 = math.getNumber2();
		return number1 + number2;
	}

}
