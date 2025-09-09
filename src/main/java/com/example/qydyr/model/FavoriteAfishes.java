package com.example.qydyr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "favorite_afishes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteAfishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User userId;
    @ManyToOne
    @JoinColumn(name = "afisha_id")
    public Afisha afisha;

}