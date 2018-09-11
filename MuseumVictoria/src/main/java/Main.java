import repository.Repository;

public class Main {
    public static void main(String[] args) {
        Repository repo = new Repository();
        try {
            System.out.println("Searching 'painting' ");
            repo.SearchByTerm("painting", true);
            System.out.println("Searching 'watercolor' ");
            repo.SearchByTerm("watercolor", true);
            System.out.println("Searching 'culture' ");
            repo.SearchByTerm("culture", true);
            System.out.println("Searching 'costumes' ");
            repo.SearchByTerm("costumes", true);
            System.out.println("Searching 'embroidery' ");
            repo.SearchByTerm("embroidery", true);
            System.out.println("Searching 'music' ");
            repo.SearchByTerm("music", true);
            System.out.println("Searching 'history' ");
            repo.SearchByTerm("history", true);
            System.out.println("Searching 'contemporary' ");
            repo.SearchByTerm("contemporary", true);
            System.out.println("Searching 'design' ");
            repo.SearchByTerm("design", true);
            System.out.println("Searching 'story' ");
            repo.SearchByTerm("story", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
