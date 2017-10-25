package classes;

import org.apache.commons.cli.*;

public class Parse {

    public static Options option = new Options();
    private static CommandLine cmd = null;
    private CommandLineParser parser = new DefaultParser();

    /**
     * Добавление опций
     */
    public Parse() {
        option.addOption("login", true, "login");
        option.addOption("password", true, "password");
        option.addOption("role", true, "role");
        option.addOption("resource", true, "resource");
        option.addOption("dataStart", true, "dataStart");
        option.addOption("dataEnd", true, "dataEnd");
        option.addOption("volume", true, "volume");
        option.addOption("h", "help", false, "help");

    }

    public UserData parse(String[] args) throws ParseException {
        cmd = parser.parse(Parse.option, args);
        UserData userData = new UserData();

        //Записываем аргументы через set методы в класс userData//
        userData.setLogin(cmd.getOptionValue("login"));
        userData.setPassword(cmd.getOptionValue("password"));
        userData.setResource(cmd.getOptionValue("resource"));
        userData.setRole(cmd.getOptionValue("role"));
        userData.setDataStart(cmd.getOptionValue("dataStart"));
        userData.setDataEnd(cmd.getOptionValue("dataEnd"));
        userData.setVolume(cmd.getOptionValue("volume"));

        return userData;
    }

    /**
     * Справка -h
     */
    public void printHelp() {
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
    public boolean isAuthentication() {
        return cmd.hasOption("login") && cmd.hasOption("password");
    }

    /**
     * Нужна ли авторизация
     */
    public boolean isAuthorization() {
        return isAuthentication() && cmd.hasOption("resource") && cmd.hasOption("role");
    }

    /**
     * Нужен ли аккаунтинг
     */
    public boolean isAccounting() {
        return isAuthorization() && cmd.hasOption("dataStart") && cmd.hasOption("dataEnd") && cmd.hasOption("volume");
    }
}