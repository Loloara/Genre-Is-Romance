package com.loloara.genreisromance.model.domain;

import com.loloara.genreisromance.model.BaseEntity;
import lombok.*;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static lombok.AccessLevel.PROTECTED;

@Entity @Getter @Builder
@AllArgsConstructor @NoArgsConstructor(access = PROTECTED)
public class Movie extends BaseEntity {

    @Column(name = "movie_title", nullable = false)
    private String movieTitle;

    @Builder.Default
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            })
    private Set<MatchMovie> matchMovies = new HashSet<>();

    @PreRemove
    public void preRemove() {
        for(MatchMovie mv : matchMovies) {
            mv.setNullMovie();
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
        final Movie other = (Movie) o;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : super.hashCode();
    }
}