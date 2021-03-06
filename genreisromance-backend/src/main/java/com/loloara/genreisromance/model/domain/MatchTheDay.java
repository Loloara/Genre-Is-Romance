package com.loloara.genreisromance.model.domain;

import com.loloara.genreisromance.model.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static lombok.AccessLevel.PROTECTED;

@Entity @Getter @Builder
@AllArgsConstructor @NoArgsConstructor(access = PROTECTED)
public class MatchTheDay extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "match_info_id")
    @NotNull
    private MatchInfo matchInfo;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "the_day_id")
    @NotNull
    private TheDay theDay;

    public void setNullMatchInfo() {
        matchInfo = null;
    }

    public void setNullTheDay() {
        theDay = null;
    }

    @PreRemove
    private void preRemove() {
        if(matchInfo != null) {
            matchInfo.getThe_days().remove(this);
        }
        if(theDay != null) {
            theDay.getMatchTheDays().remove(this);
        }
    }
}
