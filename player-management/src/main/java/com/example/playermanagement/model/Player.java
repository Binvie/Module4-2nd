package com.example.playermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    private String name;
    private String dateOfBirth;
    private String experience;
    private String avatar;
//    @ManyToOne
//    @JoinColumn(name = "position_id")
//    private Position position;
    private String position;

}
