package test21;

import javax.persistence.*;

@Entity
@Table(name = "questions", schema = "election_machine")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String text;

    public Question() {
        super();
    }

    public Question(int id, String question) {
        super();
        this.id = id;
        this.text = question;
    }

    public Question(String question) {
        super();
        this.text = question;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return text;
    }
}
