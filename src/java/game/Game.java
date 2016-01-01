/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
 
@Entity
public class Game implements Serializable {
    private static final long serialVersionUID = 1L;
 
    // Persistent Fields:
    @Id @GeneratedValue
    Long id;

    private boolean gameStarted;
    private boolean gameFinished;
    private String name;
    private String img;
    private String description;
    
    @OneToOne( targetEntity = Scoreboard.class)
    private Scoreboard scoreboard;
    
    private Integer roundCount;
    private Integer questionCount;
    
    private Map<String, Integer> answersCurrentQuestion;
    
    @OneToMany( targetEntity=Player.class )
    private List<Player> players;
    
    @OneToMany( targetEntity=Round.class )
    private List<Round> rounds;
    
    // Constructors:
    public Game() {
        super ( );
        players = new ArrayList<>();
        rounds = new ArrayList<>();
        answersCurrentQuestion = new HashMap();
        gameStarted = false;
        gameFinished = false;
        roundCount = 1;
        questionCount = 1;
    }
    
    public Game(String name, String img, String description) {
        super ( );
        this.name = name;
        this.img = img;
        this.description = description;
        players = new ArrayList<>();
        rounds = new ArrayList<>();
        answersCurrentQuestion = new HashMap();
        gameFinished = false; 
        gameStarted = false;
        roundCount = 1;
        questionCount = 1;
    }

    public Map getAnswersCurrentQuestion() {
        return answersCurrentQuestion;
    }

    public void setAnswersCurrentQuestion(Map answersCurrentQuestion) {
        this.answersCurrentQuestion = answersCurrentQuestion;
    }
    
    public void addAnswerToQuestion(String playerName, Integer answerNumber){
        if(!answersCurrentQuestion.containsKey(playerName) && answersCurrentQuestion.size() < players.size())
        answersCurrentQuestion.put(playerName, answerNumber);
    }
    
    public void emptyAnswerMap(){
        answersCurrentQuestion.clear();
    }
    
    public Integer getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(Integer roundCount) {
        this.roundCount = roundCount;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }
    
    public Integer getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Integer questionCount) {
        this.questionCount = questionCount;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }
    
    
    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }
    
    public void setPlayers(List<Player> players){
        this.players = players;
    }
    
    public void setPlayer(Player player){
        players.add(player);
    }
    
    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }
    
    public void setRound(Round round){
        this.rounds.add(round);
    }
    
    public void checkAnswer(String playerName, Integer answer ){
        Round currentRound = this.rounds.get(this.roundCount -1);
        Question currentQuestion = currentRound.getQuestions().get(this.questionCount -1);
        if(currentQuestion.getCorrectAnswer().equals(answer))
            scoreboard.AddPoint(playerName);
    }
    
    // String Representation:
    @Override
    public String toString() {
        return name;
    }
}
    

