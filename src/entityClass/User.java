package entityClass;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @Author : 卟言呢
 * @Description : 用户的超类
 * @Date : 2021/11/23 16:04
 */
public class User implements Serializable {

    private String role;
    private String name;
    private String password;

    public User() {
        role = null;
        name = null;
        password = null;
    }

    public User(String name, String password, String role) {
        this.role = role;
        this.name = name;
        this.password = password;
    }

    public void loadFunction() {
    }

    public void getFunction() {
    }

    public List<String> getFunctionClassNames() {
        return null;
    }

    @Override
    public String toString() {
        return " name='" + name + '\'' +
                "role='" + role + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
