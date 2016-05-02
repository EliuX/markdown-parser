package errorpages;


public class MigrateErrorPages {

    public static void main(String[] args) {
        System.out.println("Importing Errors");
        ErrorsPageMigrator migrator = null;
        try {
            migrator = new ErrorsPageMigrator();
            migrator.run();
        } catch (ParsingErrFileException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
