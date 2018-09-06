import repository.Repository;

public class Main {
    public static void main(String[] args) {
        Repository repo = new Repository();
        try {
            repo.SearchByTerm("coin", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
