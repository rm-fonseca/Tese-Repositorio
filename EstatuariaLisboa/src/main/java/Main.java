import repository.Repository;

public class Main {

    public static void main(String[] args) {
        Repository repo = new Repository();
        try {
            //SearchByTerm
            repo.SearchByTerm("duarte", true);

            //SearchByBox
            //NOTE: The To arguments should be lower then From arguments
            repo.SearchByBox(0,40,-9,0, true);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
