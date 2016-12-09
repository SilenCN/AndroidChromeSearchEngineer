package cn.silen_dev.chromesearchengineer.Chrome.Engineer.Model;

/**
 * Created by silen on 16-11-20.
 */

public class ShortEngineer {
    private int id;
    private String short_name;
    private String keyword;
    private String favicon_url;
    private String url;

    private String input_encodings;

    private boolean show_in_default_list=true;
    private int prepopulate_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getFavicon_url() {
        return favicon_url;
    }

    public void setFavicon_url(String favicon_url) {
        this.favicon_url = favicon_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getInput_encodings() {
        return input_encodings;
    }

    public void setInput_encodings(String input_encodings) {
        this.input_encodings = input_encodings;
    }

    public boolean isShow_in_default_list() {
        return show_in_default_list;
    }

    public void setShow_in_default_list(boolean show_in_default_list) {
        this.show_in_default_list = show_in_default_list;
    }

    public int getPrepopulate_id() {
        return prepopulate_id;
    }

    public void setPrepopulate_id(int prepopulate_id) {
        this.prepopulate_id = prepopulate_id;
    }
}
