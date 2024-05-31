package com.binary.jpaMappingOneToMany.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Entity

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
     @NotEmpty(message = "fname Not to be empity")
    private String Sname;
     @NotBlank
    private String Sclass;
     @Min(6)
     @Max(45)
     private int age;
     @ManyToOne(fetch = FetchType.EAGER)
     @JoinColumn(name = "teacher")
     private Teacher teacher;

}
