package com.example.multientityauth.MultiEntityAuth.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends User{

    @Id
    Long id;

    private String restaurant;

    private Integer age;
}
