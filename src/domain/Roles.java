package domain;

public enum Roles {
    READ,
    WRITE,
    EXECUTE;

    /**
     * Проверка валидности роли
     */
    public static boolean isCheckValidRole(String role) {
        for (Roles roles : values()){
            if (roles.name().equals(role)) {
                return true;
            }
        }
        return false;
    }
}