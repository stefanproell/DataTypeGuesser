package at.stefanproell.LocaleHelpers;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * DataTypeGuesser
 * Created by stefan
 * {MONTH_NAME_FULL} {YEAR}
 */
public class ParseJSON {
    public ParseJSON(){

    }

    public void parse(){
        Gson gson = new Gson();


        BufferedReader br =new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream(
                        "at.stefanproell/LocaleHelpers/LanguageNames.json")));

        //convert the json string back to object
        LanguagesContainerBean langs = gson.fromJson(br, LanguagesContainerBean.class);

        System.out.println(langs);
    }
}
