/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Tomas
 */
@Entity
public class Scoreboard implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private HashMap<String, Integer> scoreboard;
    
    public Scoreboard(){
        scoreboard = new HashMap<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HashMap<String, Integer> getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(HashMap<String, Integer> scoreboard) {
        this.scoreboard = scoreboard;
    }
    
    public void AddPoint(String playerName){
        scoreboard.put(playerName, scoreboard.get(playerName) + 1);
    }
    
    public void AddPlayerToScoreboard(String playerName){
        scoreboard.put(playerName, 0);
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Scoreboard)) {
            return false;
        }
        Scoreboard other = (Scoreboard) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "game.Scoreboard[ id=" + id + " ]";
    }
    
}
