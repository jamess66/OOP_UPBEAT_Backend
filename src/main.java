import java.util.ArrayList;

public class main {
    public static void main(String[] args) throws ExprErrorException {
            int i = 0;
            FileManager reader = new FileManager();
            ArrayList<String> file =  reader.FileReader("src/ConstructionPlan");
            for (String inputString : file) {
                i++;
                ExprTokenizer tokens1 = new ExprTokenizer(inputString);
                ConstructionPlanParser h = new ConstructionPlanParser(tokens1);
                h.parse();
            }
    }
}