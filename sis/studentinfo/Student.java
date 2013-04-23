package sis.studentinfo;

import java.util.*;

public class Student {
    public enum Grade {
        A(4), B(3), C(2), D(1), F(0);
        
        private int points;
        
        Grade(int points) {
            this.points = points;
        }
        
        int getPoints() {
            return points;
        }
    }
    
    static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
    static final String IN_STATE = "CO";
    
    private String name;
    private int credits = 0;
    private String state = "";
    private List<Grade> grades = new ArrayList<Grade>();
    private GradingStrategy gradingStrategy = new BasicGradingStrategy();
    
    public Student(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
        
    int getCredits() {
        return credits;
    }
    
    void setState(String state) {
        this.state = state.toUpperCase();
    }
    
    boolean isInState() {
        return state.equals(Student.IN_STATE);
    }    
    
    boolean isFullTime() {
        return credits >= CREDITS_REQUIRED_FOR_FULL_TIME;
    }
    
    void addCredits(int credits) {
        this.credits += credits;
    }
    
    void setGradingStrategy(GradingStrategy gradingStrategy) {
        this.gradingStrategy = gradingStrategy;
    }
    
    void addGrade(Grade grade) {
        grades.add(grade);
    }
    
    double getGpa() {
        if (grades.isEmpty())
            return 0.0;
        
        double total = 0.0;
        for (Grade grade : grades)
            total += gradingStrategy.getGradePointsFor(grade);
        
        return total / grades.size();
    }
}
