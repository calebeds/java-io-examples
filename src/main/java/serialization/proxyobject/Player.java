package serialization.proxyobject;


import java.io.Serial;
import java.io.Serializable;

public class Player implements Serializable {
    private static final long serialVersionUID = -2258174123826210033L;

    private Integer level;

    private Integer points;

    private User userDetails;

    public Player() {
    }

    public Player(Integer level, Integer points, User userDetails) {
        this.level = level;
        this.points = points;
        this.userDetails = userDetails;
    }

    @Serial
    private Object writeReplace() {
        System.out.println("Inside writeReplace()");
       return new PlayerProxy(level, points, userDetails);
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public User getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(User userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public String toString() {
        return "Player{" +
                "level=" + level +
                ", points=" + points +
                ", userName='" + userDetails + '\'' +
                '}';
    }
}
