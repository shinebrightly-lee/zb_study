package main.model.dto;

import lombok.*;

@Getter
@Setter
public class History {
    private int id;
    private double lat;
    private  double lnt;
    private String inquiry_date;
}
