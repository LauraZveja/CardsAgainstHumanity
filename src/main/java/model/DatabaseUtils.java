package model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DatabaseUtils {
	private static final String URL = "jdbc:sqlite:cah.db";

	public static void createDatabase() throws SQLException, IOException {
		File dbFile = new File("cah.db");
		if (dbFile.exists()) {
			System.out.println("Datubaze jau eksiste");
			return;
		}
		try (Connection conn = DriverManager.getConnection(URL)) {
			InputStream input = DatabaseUtils.class.getClassLoader().getResourceAsStream("create_table.sql");
			String fileContent = new String(input.readAllBytes());
			String[] statements = fileContent.split(";");
			for (String statement : statements) {
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(statement);
			}
			System.out.println("Datubaze izveidota veiksmigi");
		}
	}

	public static void savePlayerToDB(Player player) throws SQLException {
		if (player == null) {
			throw new IllegalArgumentException("player nevar but null");
		}
		try (Connection conn = getConnection()) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String formattedRD = sdf.format(player.getRegistrationDate().getTime());
			String formattedDOB = sdf.format(player.getDateOfBirth().getTime());
			String query = "INSERT INTO players (id, username, password, dob, email, registerdate, gender, country ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, player.getPlayerID());
			preparedStatement.setString(2, player.getUserName());
			preparedStatement.setString(3, player.getPassword());
			preparedStatement.setString(4, formattedDOB);
			preparedStatement.setString(5, player.getEmail());
			preparedStatement.setString(6, formattedRD);
			preparedStatement.setString(7, player.getGender().toString());
			preparedStatement.setString(8, player.getCountry().toString());
			preparedStatement.executeUpdate();
			System.out.println("Player saglabats veiksmigi");
		}
	}

	public static void saveGameLobbyToDB(GameLobby gameLobby) throws SQLException {
		if (gameLobby == null) {
			throw new IllegalArgumentException("gameLobby nevar but null");
		}
		try (Connection conn = getConnection()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String formattedDAT = gameLobby.getGameDate().format(formatter);
			String query = "INSERT INTO GameLobby (id, dateandtime, roundcount ) VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, gameLobby.getGameLobby_ID());
			preparedStatement.setString(2, formattedDAT);
			preparedStatement.setInt(3, gameLobby.getRoundCount());
			preparedStatement.executeUpdate();
			System.out.println("GameLobby saglabats veiksmigi");
		}
	}

	public static void saveVotesToDB(Round round) throws SQLException {
		if (round == null) {
			throw new IllegalArgumentException("round nevar but null");
		}
		try (Connection conn = getConnection()) {
			for (Vote vote : round.getVotes()) {
				String query = "INSERT INTO GameHistory (gamelobbyid, playerid, roundid, score) VALUES (?, ?, ?, ?)";
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				preparedStatement.setInt(1, round.getGameLobby_ID());
				preparedStatement.setInt(2, vote.playerVotedAbout().getPlayerID());
				preparedStatement.setInt(3, round.getRoundID());
				preparedStatement.setInt(4, 1);
				preparedStatement.executeUpdate();
				System.out.println("Vote saglabats veiksmigi");
			}
		}
	}

	// USEFUL FUNCTIONS ZEMAK
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:sqlite:cah.db");
	}

	public static int getPlayerIdByUsername(String username) {
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement("SELECT id FROM players WHERE username = ?");
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("id");
			} else {
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public static int getTotalScoreByUsername(String username) {
		try (Connection connection = getConnection()) {
			int playerId = getPlayerIdByUsername(username);
			PreparedStatement statement = connection
					.prepareStatement("SELECT SUM(score) as total_score FROM gamehistory WHERE playerid = ?");
			statement.setInt(1, playerId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("total_score");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return 0;
	}

	public static int getTotalScoreByUsernameAndGameID(String username, int gameID) {
		try (Connection connection = getConnection()) {
			int playerId = getPlayerIdByUsername(username);
			PreparedStatement statement = connection.prepareStatement(
					"SELECT SUM(score) as total_score FROM gamehistory WHERE playerid = ? AND gamelobbyid = ?");
			statement.setInt(1, playerId);
			statement.setInt(2, gameID);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("total_score");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return 0;
	}

	public static boolean isPlayerAnAdult(String username) {
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement("SELECT dob FROM players WHERE username = ?");
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				String dob = resultSet.getString("dob");
				LocalDate dateOfBirth = LocalDate.parse(dob, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				Period age = Period.between(dateOfBirth, LocalDate.now());
				if (age.getYears() >= 18) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean isUsernameTaken(String username) {
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM players WHERE username = ?");
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();
			return resultSet.next() ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean isPlayerPasswordCorrect(String username, String password) {
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM players WHERE username = ? AND password = ?");
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			return resultSet.next() ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean isEmailTaken(String email) {
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM players WHERE email = ?");
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			return resultSet.next() ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static int getLastPlayerID() {
		try (Connection connection = getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT id FROM players ORDER BY id DESC LIMIT 1");
			if (resultSet.next()) {
				return resultSet.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int getLastLobbyID() {
		try (Connection connection = getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT id FROM GameLobby ORDER BY id DESC LIMIT 1");
			if (resultSet.next()) {
				return resultSet.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static ArrayList<Score> getTopScore() {
		ArrayList<Score> top3 = new ArrayList<>();

		try (Connection connection = getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"SELECT playerid, COUNT(playerid) as votes FROM GameHistory GROUP BY playerid ORDER BY votes DESC LIMIT 3");
			int place = 1;
			while (resultSet.next()) {
				int playerid = resultSet.getInt("playerid");
				int votes = resultSet.getInt("votes"); // votes
				PreparedStatement ps = connection.prepareStatement("SELECT username FROM players WHERE id=?");
				ps.setInt(1, playerid);
				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					String username = rs.getString("username");
					top3.add(new Score(place, username, votes));
					place++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return top3;
	}

	public static ArrayList<Score> getGameScore(int gamenumber) {
		ArrayList<Score> scores = new ArrayList<>();
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(
					"SELECT playerid, COUNT(playerid) as votes FROM GameHistory WHERE gamelobbyid = ? GROUP BY playerid ORDER BY votes DESC");
			statement.setInt(1, gamenumber);
			ResultSet resultSet = statement.executeQuery();
			int place = 1;
			while (resultSet.next()) {
				int playerid = resultSet.getInt("playerid");
				int votes = resultSet.getInt("votes"); // votes
				PreparedStatement ps = connection.prepareStatement("SELECT username FROM players WHERE id=?");
				ps.setInt(1, playerid);
				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					String username = rs.getString("username");
					scores.add(new Score(place, username, votes));
					place++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return scores;
	}

	public static boolean isPlayerAnAdmin(String username) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtils.getConnection();
			stmt = conn.prepareStatement("SELECT admin FROM Players WHERE username = ?");
			stmt.setString(1, username);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("admin") == 1;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}