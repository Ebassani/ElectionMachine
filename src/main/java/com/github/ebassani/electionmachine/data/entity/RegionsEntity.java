package com.github.ebassani.electionmachine.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "regions", schema = "election_machine")
public class RegionsEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "region")
    private String region;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegionsEntity that = (RegionsEntity) o;

        if (region != null ? !region.equals(that.region) : that.region != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return region != null ? region.hashCode() : 0;
    }
}
