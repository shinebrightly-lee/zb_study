package main.model.dto;

import lombok.*;

@Setter
@Getter
public class BookmarkGroup {
    private int  id ;
    private  String book_name;
    private int book_seq;
    private String registration_date;
    private String inquiry_date;
}
