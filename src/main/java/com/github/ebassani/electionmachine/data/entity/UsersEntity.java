package com.github.ebassani.electionmachine.data.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "election_machine")
public class UsersEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "password_hash")
    private String passwordHash;
    @Basic
    @Column(name = "is_admin")
    private boolean isAdmin;
    @Basic
    @Column(name = "is_candidate")
    private boolean isCandidate;
    @Basic
    @Column(name = "names")
    private String names;
    @Basic
    @Column(name = "surnames")
    private String surnames;
    @Basic
    @Column(name = "region")
    private String region;
    @Basic
    @Column(name = "age")
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean getIsCandidate() {
        return isCandidate;
    }

    public void setIsCandidate(boolean isCandidate) {
        this.isCandidate = isCandidate;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (id != that.id) return false;
        if (isAdmin != that.isAdmin) return false;
        if (isCandidate != that.isCandidate) return false;
        if (age != that.age) return false;
        if (!Objects.equals(email, that.email)) return false;
        if (!Objects.equals(passwordHash, that.passwordHash)) return false;
        if (!Objects.equals(names, that.names)) return false;
        if (!Objects.equals(surnames, that.surnames)) return false;
        if (!Objects.equals(region, that.region)) return false;

        return true;
    }
}
