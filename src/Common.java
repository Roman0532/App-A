import org.apache.commons.cli.*;

/**
 * Created by Roman Maximov on 21.10.2017
 */
public class Common {
    public static Options option = new Options();
    private CommandLineParser parser = new DefaultParser();
    private static CommandLine cmd = null;

    public Common() {
        option.addOption("login", true, "Логин");
        option.addOption("password", true, "Пароль");
        option.addOption("role", true, "Роль");
        option.addOption("resource", true, "Ресурс");
        option.addOption("dataStart", true, "Дата начала");
        option.addOption("dataEnd", true, "Дата окончания");
        option.addOption("volume", true, "Объем");
        option.addOption("h","help", false, "Справка");
    }

    public boolean isHelp() {
        return (cmd.hasOption("help") || cmd.hasOption("h")) || !isAuthentication();
    }

    public void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Help", option);
    }

    boolean isAuthentication() {
        return (cmd.hasOption("login") && cmd.hasOption("password"));
    }

    boolean isAuthorization() {
        return (isAuthentication() && cmd.hasOption("resource") && cmd.hasOption("role"));
    }

    boolean isAccounting() {
        return (isAuthorization() && cmd.hasOption("dataStart") && cmd.hasOption("dataEnd") && cmd.hasOption("volume"));
    }

    CustomDate Parse(String[] args) throws ParseException {
        cmd = parser.parse(Common.option, args);
        CustomDate customDate = new CustomDate();

        if (isAuthentication()) {
            customDate.setLogin(cmd.getOptionValue("login"));
            customDate.setPassword(cmd.getOptionValue("password"));
            System.out.println(customDate.getLogin());
        }

        if (isAuthorization()) {
            customDate.setResource(cmd.getOptionValue("resource"));
            customDate.setRole(cmd.getOptionValue("role"));
        }

        if (isAccounting()) {
            customDate.setDataStart(cmd.getOptionValue("dataStart"));
            customDate.setDataEnd(cmd.getOptionValue("dataEnd"));
            customDate.setVolume(cmd.getOptionValue("volume"));
        }
        return customDate;
    }

}



