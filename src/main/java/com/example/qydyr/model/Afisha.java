package com.example.qydyr.model;

import com.example.qydyr.model.enums.EventCategory;
import com.example.qydyr.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "afisha")
@SQLDelete(sql = "UPDATE afisha SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Afisha implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address_name")
    private String addressName;
    @Column(name = "address_link")
    private String addressLink;
    @Column(name = "created_date_time", columnDefinition = "datetime")
    private LocalDateTime createdDateTime;
    @Column(name = "event_date_time_from", columnDefinition = "datetime")
    private LocalDateTime eventDateTimeFrom;
    @Column(name = "event_date_time_to", columnDefinition = "datetime")
    private LocalDateTime eventDateTimeTo;
    @Column(name = "phone")
    private String phone;
    @Column(name = "description")
    private String  description;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "place")
    private Image image;
    @Column(name = "price")
    private Integer price;
    @Embedded
    private GeoLocation geoLocation;

    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private EventCategory category;

    private boolean geoProcessed;

    public Afisha(final String name, final String addressName, final String addressLink, LocalDateTime createdDateTime,
                  LocalDateTime eventDateTimeFrom, LocalDateTime eventDateTimeTo,
                  final String phone, final String description, final Integer price,
                  final Address address, final Status status, final EventCategory category, boolean geoProcessed) {
        this.name = name;
        this.addressName = addressName;
        this.addressLink = addressLink;
        this.createdDateTime = createdDateTime;
        this.eventDateTimeFrom = eventDateTimeFrom;
        this.eventDateTimeTo = eventDateTimeTo;
        this.phone = phone;
        this.description = description;
        this.price = price;
        this.address = address;
        this.status = status;
        this.category = category;
        this.geoProcessed = geoProcessed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Afisha that = (Afisha) o;
        return this.id != null && that.id.equals(this.id);
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

//    public void setImage(Image image) {
//        this.image = image;
//    }
}
