package hello.core.scan.filter;

import java.lang.annotation.*;


@Target(ElementType.TYPE) //TYPE : class레벨에 붙음
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {

    //얘가 붙은건 Component 스캔에 추가할 거임 ㅋㅋㅋㅋ
}
