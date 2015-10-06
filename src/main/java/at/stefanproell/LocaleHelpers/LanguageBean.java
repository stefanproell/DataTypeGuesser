package at.stefanproell.LocaleHelpers;

/**
 * DataTypeGuesser
 * Created by stefan
 * {MONTH_NAME_FULL} {YEAR}
 */
public class LanguageBean {
    private String code;
    private String name;
    private String nativeName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }
}
