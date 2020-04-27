package wyc.hello.idempotent.test;

import com.wyc.hello.idempotent.feature.IdempotentFeature;

public class Greeting implements IdempotentFeature {
	private long id;
	private String content;

	public Greeting() {}

	public Greeting(long id, String content) {
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String idempotentKey() {
		return String.valueOf(id);
	}
}
