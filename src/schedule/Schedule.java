import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private int scheduleId;
    private String dayOfWeek;
    private String startTime;
    private String endTime;

    // getters and setters
    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    // create table query
    public static void createTable(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS schedule (" +
                "schedule_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "day_of_week VARCHAR(255), " +
                "start_time VARCHAR(255), " +
                "end_time VARCHAR(255)" +
                ")");
    }

    // insert query
    public static void insertSchedule(Connection conn, Schedule schedule) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO schedule (day_of_week, start_time, end_time) VALUES (?, ?, ?)");
        pstmt.setString(1, schedule.getDayOfWeek());
        pstmt.setString(2, schedule.getStartTime());
        pstmt.setString(3, schedule.getEndTime());
        pstmt.executeUpdate();
    }

    // retrieve all schedules query
    public static List<Schedule> getAllSchedules(Connection conn) throws SQLException {
        List<Schedule> schedules = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM schedule");
        while (rs.next()) {
            Schedule schedule = new Schedule();
            schedule.setScheduleId(rs.getInt("schedule_id"));
            schedule.setDayOfWeek(rs.getString("day_of_week"));
            schedule.setStartTime(rs.getString("start_time"));
            schedule.setEndTime(rs.getString("end_time"));
            schedules.add(schedule);
        }
        return schedules;
    }
}