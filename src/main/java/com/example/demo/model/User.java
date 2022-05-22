package com.example.demo.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="user6")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    @Column(name="name")
    String name;

    @Column(name="email")
    String email;

    @Column(name="telephone")
    String telephone;

    public User() {
    }

    public User(int user_id, String name, String email, String telephone) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", telephone=" + telephone +
                '}';
    }
}

