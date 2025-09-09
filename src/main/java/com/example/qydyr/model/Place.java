package com.example.qydyr.model;

import com.example.qydyr.model.enums.Category;
import com.example.qydyr.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "place")
@SQLDelete(sql = "UPDATE place SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Place implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "street_name")
    private String streetName;
    @Column(name = "street_number")
    private String streetNumber;
    @Column(name = "place_link")
    private String placeLink;
    @Column(name = "description")
    private String description;
    @Column(name = "price_from")
    private Integer priceFrom;
    @Column(name = "price_to")
    private Integer priceTo;
    @Column(name = "social_network")
    private String socialNetwork;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "capacity")
    private Integer capacity;
    @Embedded
    private GeoLocation geoLocation;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "place")
    private List<Image> images = new ArrayList<>();
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;

    @Column(name = "published")
    private Boolean published = Boolean.FALSE;

    private boolean geoProcessed;

    public Place(final String name, final String streetName, final String streetNumber,
                 final String placeLink, final String description, final Integer priceFrom,
                 final Integer priceTo, final String socialNetwork, final String phone,
                 final String email, final Integer capacity, final Address address, final Status status, final Category category, boolean geoProcessed, Boolean published ) {
    this.name = name;
    this.streetName = streetName;
    this.streetNumber = streetNumber;
    this.placeLink = placeLink;
    this.description = description;
    this.priceFrom = priceFrom;
    this.priceTo = priceTo;
    this.socialNetwork = socialNetwork;
    this.phone = phone;
    this.email = email;
    this.capacity = capacity;
    this.address = address;
    this.status = status;
    this.category = category;
    this.geoProcessed = geoProcessed;
    this.published = published;
    }

    public void addImageToPlace(Image image) {
        image.setPlace(this);
        images.add(image);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place that = (Place) o;
        return this.id != null && that.id.equals(this.id);
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}