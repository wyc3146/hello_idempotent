package wyc.hello.idempotent.test;

import com.wyc.hello.idempotent.Idempotent;
import com.wyc.hello.idempotent.feature.IdempotentKey;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController        // 是 @Controller 和 @ResponseBody 一起使用的简写
public class HelloController {

    private static final String template = "Hello, %s!";

	/**
	 * 指定参数为幂等key
	 * @param name
	 * @return
	 */
	@RequestMapping("/hello")
	@Idempotent
	public Greeting greeting(@IdempotentKey @RequestParam(defaultValue = "World") String name) {
        return new Greeting(1, String.format(template, name));
	}

	/**
	 * 参数实现了幂等特征接口
	 * @param greeting
	 * @return
	 */
	@Idempotent
	@RequestMapping("/hehe")
	public Greeting gege(@RequestBody Greeting greeting) {
		return greeting;
	}

	/**
	 * 参数对象字段有幂等注解
	 * @param greeting2
	 * @return
	 */
	@Idempotent
	@RequestMapping("/haha")
	public Greeting2 haha(@RequestBody Greeting2 greeting2) {
		return greeting2;
	}
}
