package at.stefanproell.LocaleHelpers;

import java.util.List;

/**
 * DataTypeGuesser
 * Created by stefan
 * {MONTH_NAME_FULL} {YEAR}
 */
public class LanguagesContainerBean {
    private List<LanguageBean> languages;

    public List<LanguageBean> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageBean> languages) {
        this.languages = languages;
    }
}
