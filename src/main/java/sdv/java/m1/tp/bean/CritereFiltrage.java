package sdv.java.m1.tp.bean;

import java.util.ArrayList;
import java.util.List;

public class CritereFiltrage {
    private List<String> criticites;
    private String sousChaine;

    public CritereFiltrage() {
        this.criticites = new ArrayList<>();
        this.sousChaine = "";
    }

    public List<String> getCriticites() {
        return criticites;
    }

    public void setCriticites(List<String> criticites) {
        this.criticites = criticites;
    }

    public String getSousChaine() {
        return sousChaine;
    }

    public void setSousChaine(String sousChaine) {
        this.sousChaine = sousChaine;
    }

    @Override
    public String toString() {
        return "CritereFiltrage{" +
                "criticites=" + criticites +
                ", sousChaine='" + sousChaine + '\'' +
                '}';
    }
}
