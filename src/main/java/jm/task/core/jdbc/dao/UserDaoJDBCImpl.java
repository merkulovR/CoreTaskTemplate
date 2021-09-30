package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util util = new Util();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        Connection con = util.getConnection();
        Statement statement = null;

        try {
            statement = con.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                    "id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(20) NOT NULL, " +
                    "lastName VARCHAR(20) NOT NULL, " +
                    "age TINYINT NOT NULL)");

            try {
                con.commit();
            } catch (SQLException e) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.err.println("Не удалось закрыть Statement!");
            }

            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Не удалось закрыть Connection!");
            }
        }
    }

    public void dropUsersTable() {
        Connection con = util.getConnection();
        Statement statement = null;

        try {
            statement = con.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS users");

            try {
                con.commit();
            } catch (SQLException e) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.err.println("Не удалось закрыть Statement!");
            }

            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Не удалось закрыть Connection!");
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection con = util.getConnection();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setInt(3, age);
            ps.executeUpdate();

            try {
                con.commit();
                System.out.println("User с именем - " + name + " добавлен в базу данных");
            } catch (SQLException e) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                System.err.println("Не удалось закрыть Statement!");
            }

            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Не удалось закрыть Connection!");
            }
        }
    }

    public void removeUserById(long id) {
        Connection con = util.getConnection();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("DELETE FROM users WHERE id = (?)");
            ps.setLong(1, id);
            ps.executeUpdate();

            try {
                con.commit();
            } catch (SQLException e) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                System.err.println("Не удалось закрыть Statement!");
            }

            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Не удалось закрыть Connection!");
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection con = util.getConnection();
        ResultSet resultSet = null;
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("SELECT * FROM users");

            try {
                resultSet = ps.executeQuery();


                while (resultSet.next()) {
                    users.add(new User(resultSet.getString("name"),
                            resultSet.getString("lastName"),
                            resultSet.getByte("age")));
                    users.get(users.size() - 1).setId(resultSet.getLong("id"));
                }
            } catch (SQLException ignored) {
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException e) {
                    System.err.println("Не удалось закрыть ResultSet!");
                }
            }

        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                System.err.println("Не удалось закрыть Statement!");
            }

            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Не удалось закрыть Connection!");
            }
        }

        return users;
    }

    public void cleanUsersTable() {
        Connection con = util.getConnection();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("TRUNCATE TABLE users");
            ps.executeUpdate();

            try {
                con.commit();
            } catch (SQLException e) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                System.err.println("Не удалось закрыть Statement!");
            }

            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Не удалось закрыть Connection!");
            }
        }
    }
}
