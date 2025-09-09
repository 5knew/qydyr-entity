package com.example.qydyr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "favorite_places")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoritePlaces {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User userId;
    @ManyToOne
    @JoinColumn(name = "place_id")
    public Place place;

}
