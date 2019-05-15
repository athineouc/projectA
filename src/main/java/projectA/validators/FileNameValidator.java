package projectA.validators;


public class FileNameValidator {


    public static boolean validate(String fileName) {
        String csv = "csv";
        return fileName.substring(fileName.length() - 3).equals(csv);
    }
}
