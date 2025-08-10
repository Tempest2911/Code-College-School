package Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "Destinations")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Nationalized
    @Column(name = "country", nullable = false, length = 100)
    private String country;

    @Column(name = "budget_est", nullable = false)
    private Integer budgetEst;

    @Column(name = "is_visited", nullable = false)
    private Boolean isVisited = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getBudgetEst() {
        return budgetEst;
    }

    public void setBudgetEst(Integer budgetEst) {
        this.budgetEst = budgetEst;
    }

    public Boolean getIsVisited() {
        return isVisited;
    }

    public void setIsVisited(Boolean isVisited) {
        this.isVisited = isVisited;
    }

    public Destination(Integer id, String name, String country, Integer budgetEst, Boolean isVisited) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.budgetEst = budgetEst;
        this.isVisited = isVisited;
    }

    public Destination() {

    }
}