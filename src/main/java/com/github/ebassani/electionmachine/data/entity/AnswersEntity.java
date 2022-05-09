package com.github.ebassani.electionmachine.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "answers", schema = "election_machine")
@IdClass(AnswersEntityPK.class)
public class AnswersEntity {
    @Id
    @Column(name = "question_id")
    private int questionId;
    @Id
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "value")
    private int value;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswersEntity that = (AnswersEntity) o;

        if (questionId != that.questionId) return false;
        if (userId != that.userId) return false;
        if (value != that.value) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = questionId;
        result = 31 * result + userId;
        result = 31 * result + value;
        return result;
    }
}
