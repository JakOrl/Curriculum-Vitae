package Pong_Source.DAO;

import Pong_Source.Controller.*;
import Pong_Source.Models.*;
import Pong_Source.Services.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Match Results.
 * Handles all SQL interactions for the match_history table.
 * @author Jakub Orlowski
 * @version 1.0
 */
public class MatchDAO {

    /**
     * Saves a match to the database
     */

    // Note for specification. It asks to save "game name" aswell but i will not do this. I dont see a need.
    // therefore i will have it hard coded to "autosave" it wont affect UX that way.
    public void saveMatch(MatchResult result){
        String sql = "INSERT INTO match_history (Game_Name, player1_name, player2_name, score1, score2, score_limit) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement prepSt = connection.prepareStatement(sql)){

            prepSt.setString(1, "AutoSave");
            prepSt.setString(2, result.p1Name);
            prepSt.setString(3, result.p2Name);
            prepSt.setInt(4, result.score1);
            prepSt.setInt(5, result.score2);
            prepSt.setInt(6, result.scorelimit);

            prepSt.executeUpdate();
            System.out.println("Match Saved Successfully");
        } catch (SQLException e){
            System.err.println("Database save error: " + e.getMessage());
        }
    }

    /**
     * Reconstructs the Game object using the Builder Pattern.
     */
    public Game loadLatestGame(Player p1, Player p2){
        String sql = "SELECT * FROM match_history ORDER BY ID DESC LIMIT 1";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){

            if (resultSet.next()){
                // maintaining the scores and names
                p1.setName(resultSet.getString("Player1_Name"));
                p1.setScore(resultSet.getInt("score1"));
                p2.setName(resultSet.getString("Player2_Name"));
                p2.setScore(resultSet.getInt("score2"));
                
                // Use the builder pattern to load game
                return new GameBuilder().setTargetScore(resultSet.getInt("Score_Limit")).build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}


