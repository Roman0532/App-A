import org.apache.commons.cli.*;

public class Parse {
    public static Options option = new Options();
    private static CommandLine cmd = null;
    private CommandLineParser parser = new DefaultParser();

    public Parse() {
        option.addOption("login", true, "Логин");
        option.addOption("password", true, "Пароль");
        option.addOption("role", true, "Роль");
        option.addOption("resource", true, "Ресурс");
        option.addOption("dataStart", true, "Дата начала");
        option.addOption("dataEnd", true, "Дата окончания");
        option.addOption("volume", true, "Объем");
        option.addOption("h", "help", false, "Справка");
    }

    public void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Help", option);
    }

    /**
     * Нужна ли справка
     */
    public boolean isHelp() {
        return cmd.hasOption("help") || cmd.hasOption("h") || !isAuthentication();
    }

    /**
     * Нужна ли аутентификация
     */
    boolean isAuthentication() {
        return cmd.hasOption("login") && cmd.hasOption("password");
    }

    /**
     * Нужна ли авторизация
     */
    boolean isAuthorization() {
        return isAuthentication() && cmd.hasOption("resource") && cmd.hasOption("role");
    }

    /**
     * Нужен ли аккаунтинг
     */
    boolean isAccounting() {
        return isAuthorization() && cmd.hasOption("dataStart") && cmd.hasOption("dataEnd") && cmd.hasOption("volume");
    }

    UserData parse(String[] args) throws ParseException {
        cmd = parser.parse(Parse.option, args);
        UserData userData = new UserData();

            userData.setLogin(cmd.getOptionValue("login"));
            userData.setPassword(cmd.getOptionValue("password"));
            userData.setResource(cmd.getOptionValue("resource"));
            userData.setRole(cmd.getOptionValue("role"));
            userData.setDataStart(cmd.getOptionValue("dataStart"));
            userData.setDataEnd(cmd.getOptionValue("dataEnd"));
            userData.setVolume(cmd.getOptionValue("volume"));

        return userData;
    }
}