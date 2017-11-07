package service;

import org.apache.commons.cli.*;

public class ParseService {

    private static Options option = new Options();

    /**
     * Добавление опций
     */
    public ParseService() {

        option.addOption("login", true, "login");
        option.addOption("password", true, "password");
        option.addOption("role", true, "role");
        option.addOption("resource", true, "resource");
        option.addOption("dateStart", true, "dateStart");
        option.addOption("dateEnd", true, "dateEnd");
        option.addOption("volume", true, "volume");
        option.addOption("h", "help", false, "help");
    }

    public UserData parse(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(ParseService.option, args);
        UserData userData = new UserData();

        //Записываем аргументы через set методы в класс userData//
        userData.setLogin(cmd.getOptionValue("login"));
        userData.setPassword(cmd.getOptionValue("password"));
        userData.setResource(cmd.getOptionValue("resource"));
        userData.setRole(cmd.getOptionValue("role"));
        userData.setDataStart(cmd.getOptionValue("dateStart"));
        userData.setDataEnd(cmd.getOptionValue("dateEnd"));
        userData.setVolume(cmd.getOptionValue("volume"));

        return userData;
    }

    /**
     * Справка
     */
    public static void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Help", option);
    }
}