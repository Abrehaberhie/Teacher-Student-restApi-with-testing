package com.binary.jpaMappingOneToMany.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @NotEmpty(message = "first name must be provided")
    private   String firstName;
    @NotBlank(message = "last name is required")
   private String lastName;

   private String teachingSubject;
    @Min(6)
    @Max(45)
    private int age;
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher", fetch = FetchType.EAGER)
   @JsonIgnore
   List<Student> stuList= new ArrayList<Student>();

}
