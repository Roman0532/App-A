package service;

import org.apache.commons.cli.*;

public class ParseService {

    public static Options option = new Options();
    private CommandLineParser parser = new DefaultParser();
    static CommandLine cmd = null;
    /**
     * Добавление опций
     */
    public ParseService() {
        option.addOption("login", true, "login");
        option.addOption("password", true, "password");
        option.addOption("role", true, "role");
        option.addOption("resource", true, "resource");
        option.addOption("dataStart", true, "dataStart");
        option.addOption("dataEnd", true, "dataEnd");
        option.addOption("volume", true, "volume");
        option.addOption("h", "help", false, "help");

    }

    public UserDataService parse(String[] args) throws ParseException {
        cmd = parser.parse(ParseService.option, args);
        UserDataService userData = new UserDataService();

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
     * Справка
     */
    public void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Help", option);
    }
}