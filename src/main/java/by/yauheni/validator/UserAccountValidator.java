package by.yauheni.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserAccountValidator {

    public boolean isNormalLength(String str, int min, int max) {
        return str.length() >= min && str.length() <= max;
    }

    public boolean isLatinLetters(String str) {
        Pattern pattern = Pattern.compile("[a-z]+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public boolean hasNumberAndSymbol(String str){
        Pattern pattern1 = Pattern.compile("[a-z]{1,15}", Pattern.CASE_INSENSITIVE);
        Pattern pattern2 = Pattern.compile("[\\d]{1,15}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern1.matcher(str);
        boolean bool1 = matcher.find();
        matcher= pattern2.matcher(str);
        boolean bool2 = matcher.find();
        return bool1&bool2;
    }

}
