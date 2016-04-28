public class MigrateErrorPages {

    public static void main(String[] args)
    {
        System.out.println("Importing Errors");
        ErrorsPageMigrator migrator = new ErrorsPageMigrator();
        migrator.run();
    }
}
