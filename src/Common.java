import org.apache.commons.cli.*;

/**
 * Created by Roman Maximov on 21.10.2017
 */
public class Common {
    public static Options option = new Options();
    private CommandLineParser parser = new DefaultParser();
    private static CommandLine cmd = null;

    public Common() {
        option.addOption("login", true, "login");
        option.addOption("password", true, "password");
        option.addOption("role", true, "role");
        option.addOption("resource", true, "resource");
        option.addOption("dataStart", true, "dataStart");
        option.addOption("dataEnd", true, "dataEnd");
        option.addOption("volume", true, "Volume");
        option.addOption( "help", false, "help");
    }
    public boolean isHelp(){
        return (cmd.hasOption("help") || cmd.hasOption(null));
    }

    public static void help(){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Main",option);
    }


    public boolean isAuthentication() {
        return (cmd.hasOption("login") && cmd.hasOption("password"));
    }

    public boolean isAuthorization() {
        return (isAuthentication() && cmd.hasOption("resource") && cmd.hasOption("role"));
    }

    public boolean isAccounting() {
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



