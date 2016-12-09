package cn.silen_dev.chromesearchengineer.Chrome.Engineer.Model;

/**
 * Created by silen on 16-11-20.
 */

public class Engineer extends ShortEngineer {

    private boolean safe_for_autoreplace=true;
    private String originating_url=null;
    private long date_create=0;
    private int usage_count=0;
    private boolean show_in_default_list=true;
    private String suggest_url=null;

    private boolean create_by_policy=true;
    private String instant_url=null;
    private long last_modified=0;
    private String sync_guid=null;
    private String alternate_urls="[]";
    private String search_terms_replacement_key=null;
    private String image_url=null;
    private String image_url_post_params=null;
    private String new_tab_url=null;




    public boolean isSafe_for_autoreplace() {
        return safe_for_autoreplace;
    }

    public void setSafe_for_autoreplace(boolean safe_for_autoreplace) {
        this.safe_for_autoreplace = safe_for_autoreplace;
    }

    public String getOriginating_url() {
        return originating_url;
    }

    public void setOriginating_url(String originating_url) {
        this.originating_url = originating_url;
    }

    public long getDate_create() {
        return date_create;
    }

    public void setDate_create(long date_create) {
        this.date_create = date_create;
    }

    public int getUsage_count() {
        return usage_count;
    }

    public void setUsage_count(int usage_count) {
        this.usage_count = usage_count;
    }



    public boolean isShow_in_default_list() {
        return show_in_default_list;
    }

    public void setShow_in_default_list(boolean show_in_default_list) {
        this.show_in_default_list = show_in_default_list;
    }

    public String getSuggest_url() {
        return suggest_url;
    }

    public void setSuggest_url(String suggest_url) {
        this.suggest_url = suggest_url;
    }



    public boolean isCreate_by_policy() {
        return create_by_policy;
    }

    public void setCreate_by_policy(boolean create_by_policy) {
        this.create_by_policy = create_by_policy;
    }

    public String getInstant_url() {
        return instant_url;
    }

    public void setInstant_url(String instant_url) {
        this.instant_url = instant_url;
    }

    public long getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(long last_modified) {
        this.last_modified = last_modified;
    }

    public String getSync_guid() {
        return sync_guid;
    }

    public void setSync_guid(String sync_guid) {
        this.sync_guid = sync_guid;
    }

    public String getAlternate_urls() {
        return alternate_urls;
    }

    public void setAlternate_urls(String alternate_urls) {
        this.alternate_urls = alternate_urls;
    }

    public String getSearch_terms_replacement_key() {
        return search_terms_replacement_key;
    }

    public void setSearch_terms_replacement_key(String search_terms_replacement_key) {
        this.search_terms_replacement_key = search_terms_replacement_key;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_url_post_params() {
        return image_url_post_params;
    }

    public void setImage_url_post_params(String image_url_post_params) {
        this.image_url_post_params = image_url_post_params;
    }

    public String getNew_tab_url() {
        return new_tab_url;
    }

    public void setNew_tab_url(String new_tab_url) {
        this.new_tab_url = new_tab_url;
    }
}
