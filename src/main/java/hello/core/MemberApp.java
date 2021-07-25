package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {

       // AppConfig appConfig = new AppConfig();

        //MemberService memberService = appConfig.memberService();
     //   MemberService memberService = new MemberServiceImpl();

        //spring container, appConfig의 설정 정보를 가지고 bean으로 등록하여 관리함.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        //Appconfig에서 찾아오는게 아니라 applicationContext에서 찾아옴.
        //컨테이너에는 @Bean붙인 메서드의 이름으로 등록됨
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "A", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());

    }
}

