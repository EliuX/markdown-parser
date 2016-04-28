
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InvalidPropertiesFormatException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ehernandez on 28/04/16.
 */
public class ErrorModel {

    private final Integer errorId;

    private final String content;

    private final String title;

    private final String body;

    private final String urlTag;

    private static final Pattern FILE_PATTERN = Pattern.compile("(.+?)\\.[md]+$");

    private static final Pattern TITLE_PATTERN = Pattern.compile("[\\# \\\\w+ \\- ][0-9]{5}");

    public ErrorModel(File errFile) throws ParsingErrFileException{
        Matcher m = FILE_PATTERN.matcher(errFile.getName());
        if (m.find()) {
            errorId = Integer.valueOf(m.group(1));
            if(errorId == null)
            {
                throw new ParsingErrFileException(errFile + " has not the right format");
            }
        } else{
            throw new ParsingErrFileException(errFile + " Is not an error file");
        }
        try{
            content = readFile(errFile.getPath(), StandardCharsets.UTF_8);
        }catch(Exception ex)
        {
            throw new ParsingErrFileException(String.format("Error while reading file '%s': %s", errFile.getName(), ex.getMessage()));
        }
        //Parseando contenido
        Scanner scanner = new Scanner(content);
        title = scanner.nextLine();
        scanner.
        scanner.close();
        m = TITLE_PATTERN.matcher(errFile.getName());
        Integer errNumberFound = null;
        if (m.find()) {
            errNumberFound = Integer.valueOf(m.group(1));
        }
        if(errNumberFound!=null)
        {
            throw new ParsingErrFileException(String.format("Title in file %s is wrong: %s", errFile.getName(), title));
        }
    }


    public String getContent() {
        return content;
    }

    public String getBody() {
        return body;
    }

    public static Pattern getFilePattern() {
        return FILE_PATTERN;
    }

    public int getErrorId() {
        return errorId;
    }

    public String getUrlTag()
    {
        return errorId.toString();
    }

    public String getTitle()
    {
        return title;
    }

    private final String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    @Override
    public String toString() {
        return String.format("%s: %s", getTitle(), ge);
    }
}
