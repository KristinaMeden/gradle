package hello;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;


@RestController
public class GreetingController {

    private static final String template = "Group: %s - entering year is %s, course is %s";
    private final AtomicLong counter = new AtomicLong();
    


   @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="group", defaultValue="input a group") String group) {
		String course = "";
		
		char [] numbers= group.toCharArray();
		Calendar calendar = Calendar.getInstance();
		String currentYear= Integer.toString(calendar.get (Calendar.YEAR));
		int currentMonth =calendar.get (Calendar.MONTH);
		char [] numbers2 = currentYear.toCharArray();
		
		numbers2[3] = numbers[0];
		StringBuilder sb = new StringBuilder();
		for(int i= 0; i < numbers2.length; i++) {
		   sb.append(numbers2[i]);
		  
		}
		  String enteringYear = sb.toString();
		 if(currentMonth > 6) {
			 course =Integer.toString(Integer.parseInt(currentYear)- Integer.parseInt(enteringYear)+1);
		 } else {
			 course = Integer.toString(Integer.parseInt(currentYear) - Integer.parseInt(enteringYear));
		 }
		 if (Integer.parseInt(course)>4) {
			 course = "Gratuauted!";
		 }
        		return new Greeting(counter.incrementAndGet(),
                            String.format(template, group, enteringYear, course));
    }
}