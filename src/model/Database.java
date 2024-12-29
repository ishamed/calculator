package model;

import model.dataStructure.ArrayList;
import model.dataStructure.LinkedQueue;

public class Database {
    private static Database database ;
    private ArrayList<Variable> answers;
    private boolean[] inconsistencyManager ;
    private boolean[][] circularDependency ;
    private Database(){
        inconsistencyManager = new boolean[26];
        circularDependency = new boolean[26][26];
        equations = new LinkedQueue<>();
        answers = new ArrayList<>();
    }

    public static Database getDatabase() {
        if (database== null){
            database = new Database();
        }
        return database ;
    }
    private LinkedQueue<Variable> equations ;

    public LinkedQueue<Variable> getEquations() {
        return equations;
    }

    public void setEquations(LinkedQueue<Variable> equations) {
        this.equations = equations;
    }

    public boolean[] getInconsistencyManager() {
        return inconsistencyManager;
    }

    public void setInconsistencyManager(boolean[] inconsistencyManager) {
        this.inconsistencyManager = inconsistencyManager;
    }

    public ArrayList<Variable> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Variable> answers) {
        this.answers = answers;
    }

    public boolean[][] getCircularDependency() {
        return circularDependency;
    }

    public void setCircularDependency(boolean[][] circularDependency) {
        this.circularDependency = circularDependency;
    }
}
