package serialization.proxyobject;

import java.io.Serial;
import java.io.Serializable;

public class PlayerProxy implements Serializable {
    @Serial
    private static final long serialVersionUID = -2258174123826210033L;

    private Integer level;

    private Integer points;

    private User userDetails;

    public PlayerProxy(Integer level, Integer points, User userDetails) {
        System.out.println("To create proxy object");
        this.level = level;
        this.points = points;
        this.userDetails = userDetails;
    }

    @Serial
    private Object readResolve() {
        System.out.println("Inside readResolve()");
        return new Player(level, points, userDetails);
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
}
