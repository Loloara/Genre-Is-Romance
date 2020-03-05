package com.loloara.genreisromance.model.domain;

import com.loloara.genreisromance.model.BaseEntity;
import lombok.*;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static lombok.AccessLevel.PROTECTED;

@Entity @Getter @Builder
@AllArgsConstructor @NoArgsConstructor(access = PROTECTED)
public class Place extends BaseEntity {

    @Column(name = "place_name", nullable = false)
    private String placeName;

    @Builder.Default
    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            })
    private Set<MatchPlace> matchPlaces = new HashSet<>();

    @PreRemove
    public void preRemove() {
        for(MatchPlace mp : matchPlaces) {
            mp.setNullPlace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Place other = (Place) o;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : super.hashCode();
    }
}