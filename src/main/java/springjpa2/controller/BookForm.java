package springjpa2.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookForm {
    private Long id;

    private int stockQuantity;
    private int price;
    private String name;

    private String author;
    private String isbn;

}
