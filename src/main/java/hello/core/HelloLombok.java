package hello.core;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setAge(1);
        helloLombok.setName(":dddd");
        int age = helloLombok.getAge();
        String name = helloLombok.getName();
        System.out.println(helloLombok.toString());
    }
}
