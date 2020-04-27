package wyc.hello.idempotent.test;

import com.wyc.hello.idempotent.feature.IdempotentKey;

/**
 * created on 2020-04-27 14:25
 *
 * @author WangYongcan
 */
public class Greeting2 {
    @IdempotentKey
    private long id;
    private String content;

    public Greeting2() {}

    public Greeting2(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

}
