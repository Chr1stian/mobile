package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

/**
 * Created by Christian on 21.02.2017.
 */
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {
    private static String mysqlAddr = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11164632?allowMultiQueries=true";
    private static String mysqlUser = "sql11164632";
    private static String mysqlPass = "JKb6SqBp59";

    public Database (String adr, String user, String password){
        this.mysqlAddr = adr;
        this.mysqlUser = user;
        this.mysqlPass = password;
    }

    public static String test(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM bruker");

            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                return rs.getString(2);
            conn.close();
            return null;

        }
        catch(SQLException e){
            return e.toString();
        }
    }
    //returns ArrayList of courses from database
    public static ArrayList<Course> getCourses(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Course> courseList = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM course");

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                ArrayList<String> course = new ArrayList<>();
                for(int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++){
                    course.add(rs.getString(i));
                }courseList.add(new Course(course.get(0), course.get(1), course.get(2)));
            }
            conn.close();
            return courseList;
        }
        catch(SQLException e){
            return courseList;
        }
    }
    //returns ArrayList of lectures from database
    public static ArrayList<Lecture> getLectures(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Lecture> lectureList = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM lecture");

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                ArrayList<String> lecture = new ArrayList<>();
                for(int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++){
                    lecture.add(rs.getString(i));
                }lectureList.add(new Lecture(lecture.get(0), lecture.get(1), lecture.get(2), lecture.get(3)));
            }
            conn.close();
            return lectureList;
        }
        catch(SQLException e){
            return lectureList;
        }
    }
    //returns lectureID from database
    public static Integer getLectureID(Integer courseID, Integer number){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Integer lectureID = null;
        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT lectureID FROM lecture WHERE courseID = " + courseID.toString() +
                    " AND number = " + number.toString());

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                lectureID = Integer.parseInt(rs.getString(1));
            }
            conn.close();
            return lectureID;
        }
        catch(SQLException e){
            System.out.println(e);
            lectureID = 999;
            return lectureID;
        }
    }

    //returns number of topics for selected lecture from database
    public static Integer countTopics(Integer lectureID){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Integer numberOfTopics = null;
        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM topic WHERE lectureID = " + lectureID.toString());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                numberOfTopics = Integer.parseInt(rs.getString(1));
            }
            conn.close();
            return numberOfTopics;
        }
        catch(SQLException e){
            System.out.println(e);
            numberOfTopics = 9;
            return numberOfTopics;
        }
    }
    //returns topic from database
    public static ArrayList<Topic> getTopics(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Topic> topicList = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM topic");

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                ArrayList<String> topic = new ArrayList<>();
                for(int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++){
                    topic.add(rs.getString(i));
                }topicList.add(new Topic(topic.get(0), topic.get(1), topic.get(2), topic.get(3)));
            }
            conn.close();
            return topicList;
        }
        catch(SQLException e){
            return topicList;
        }
    }
    //returns topicID from database
    public static Integer getTopicID(Integer number, Integer lectureID){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Integer topicID = null;

        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT topicID FROM topic WHERE number = " + number.toString() +
                    " AND lectureID = " + lectureID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                topicID = Integer.parseInt(rs.getString(1));
            }
            conn.close();
            return topicID;
        }
        catch(SQLException e){
            System.out.println(e);
            topicID = null;
            return topicID;
        }


    }

    //Uploading the rating to the database
    public static String setRating(Integer topicID, Integer stars){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Integer rating = null;
        Integer userID = 1;
        String error ="Rating submitted";
        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO rating(topicID,userID,stars) VALUES ('" + topicID.toString() + "','" + userID.toString() + "','" + stars.toString() + "')");
            stmt.execute();
            conn.close();
        }
        catch(SQLException e){
            System.out.println(e);
            error = "Database error:" + e;
            return error;
        }
        return error;
    }
    //Checks if nickname is already registered in database
    public static boolean checkNickname(String nickname){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        boolean exists;
        Integer check = null;

        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM user WHERE name = '" + nickname + "'") ;
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                check = Integer.parseInt(rs.getString(1));
            }
            if(check > 0){
                exists = false;
            }
            else{
                exists = true;
            }
            conn.close();
            return exists;
        }
        catch(SQLException e){
            return false;
        }
    }
    //registers username to database
    public static void registerNickname(String nickname){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO user(name) VALUES ('" + nickname + "')");
            stmt.execute();
            conn.close();

        }
        catch(SQLException e){
        }
    }

    //send Questions to Database
    public static String sendQuestion(Integer topicID, String questionString) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Integer userID = 1;
        String answer = "Not answered yet..";
        String error ="*Question submitted*";
        Integer rating = 0;

        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO question(topicID,userID,question,answer,rating) VALUES ('" + topicID.toString() + "','" + userID.toString() + "','" + questionString +"','" + answer +"','" + rating.toString() + "')");
            //INSERT INTO `question`(`questionID`, `topicID`, `userID`, `question`, `answer`) VALUES ([value-1],[value-2],[value-3],[value-4],[value-5])
            stmt.execute();
            conn.close();
        }
        catch(SQLException e){
            System.out.println(e);
            error = "Database error:" + e;
            return error;
        }
        return error;

    }

    //get Questions from Database
    public static ArrayList<String> getQuestions(Integer topicID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> questions = new ArrayList<String>();
        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM question WHERE topicID = " + topicID.toString() +" ORDER BY rating DESC");
//SELECT `questionID`, `topicID`, `userID`, `question`, `answer`, `rating` FROM `question` WHERE 1
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                questions.add( rs.getString(1)+"Question: "+ rs.getString(4) + " Answer: " + rs.getString(5));

            }
            conn.close();
            return questions;
        }
        catch(SQLException e){
            System.out.println(e);
            questions.add("No questions yet.." + e);
            return questions;
        }
    }

    public static String setQuestionRating(Integer questionID){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Integer rating=null;
        //Integer userID = 1;
        String error ="!";

        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT rating FROM question WHERE questionID =" +questionID);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Integer Outrating = Integer.parseInt(rs.getString(1));
                rating = Outrating +1;
            }

            PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO question(rating) VALUES ('" + rating.toString()+ "') WHERE questionID= "+ questionID);
            stmt2.execute();
//"INSERT INTO question(topicID,userID,question,answer,rating) VALUES ('" + topicID.toString() + "','" + userID.toString() + "','" + questionString +"','" + answer +"','" + rating.toString() + "')");
            conn.close();

        }
        catch(SQLException e){
            System.out.println(e);
            error = "Database error:" + e;
            return error;
        }
        return error;
    }

}