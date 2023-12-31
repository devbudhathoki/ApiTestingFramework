package com.apitesting.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder(setterPrefix = "set")
@ToString
@JsonSerialize
@Getter
public class Client {

    private int id;
    private String name;
    private String age;
    private Address address;
}
