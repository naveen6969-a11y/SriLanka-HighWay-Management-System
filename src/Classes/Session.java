package Classes;

public class Session {

    private static String username;
    private static String role;

    public static void setUser(String user, String userRole) {
        username = user;
        role = userRole;
    }

    public static String getUsername() {
        return username;
    }

    public static String getRole() {
        return role;
    }

    public static boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(role);
    }

    public static boolean isCashier() {
        return "CASHIER".equalsIgnoreCase(role);
    }

    public static void clear() {
        username = null;
        role = null;
    }
}
