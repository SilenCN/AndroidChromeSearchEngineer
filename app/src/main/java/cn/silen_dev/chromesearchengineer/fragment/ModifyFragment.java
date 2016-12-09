package cn.silen_dev.chromesearchengineer.fragment;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.widget.Toast;

import cn.silen_dev.chromesearchengineer.Chrome.Engineer.Server.EngineerServer;
import cn.silen_dev.chromesearchengineer.R;


/**
 * Created by 10397 on 2016/3/24.
 */
public class ModifyFragment extends PreferenceFragment {
    EditTextPreference name, keyword, url, input_encodings, prepopulate;
    SwitchPreference show;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting_preference_dependencies);

        show = (SwitchPreference) findPreference("show_in_default_list");
        show.setChecked(EngineerServer.shortEngineer.isShow_in_default_list());
        show.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                EngineerServer.shortEngineer.setShow_in_default_list(show.isChecked());
                setShowChangeToSetId();
                return true;
            }
        });
        name = (EditTextPreference) findPreference("name");
        name.setText(EngineerServer.shortEngineer.getShort_name());
        name.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                if (o.toString().equals("")) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.modify_enter_null_error), Toast.LENGTH_SHORT).show();
                    return false;
                } else {
                    EngineerServer.shortEngineer.setShort_name(o.toString());
                    return true;
                }
            }
        });

        keyword = (EditTextPreference) findPreference("keyword");
        keyword.setText(EngineerServer.shortEngineer.getKeyword());
        keyword.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                if (o.toString().equals("")) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.modify_enter_null_error), Toast.LENGTH_SHORT).show();
                    return false;
                } else {
                    EngineerServer.shortEngineer.setKeyword(o.toString());
                    return true;
                }
            }
        });
        url = (EditTextPreference) findPreference("url");
        url.setText(EngineerServer.shortEngineer.getUrl());
        url.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                if (o.toString().equals("")) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.modify_enter_null_error), Toast.LENGTH_SHORT).show();
                    return false;
                } else {
                    EngineerServer.shortEngineer.setUrl(o.toString());
                    return true;
                }
            }
        });
        input_encodings = (EditTextPreference) findPreference("input_encodings");
        input_encodings.setText(EngineerServer.shortEngineer.getInput_encodings());
        input_encodings.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {

                EngineerServer.shortEngineer.setInput_encodings(o.toString());
                return true;
            }
        });
        prepopulate = (EditTextPreference) findPreference("prepopulate_id");
        prepopulate.setText(EngineerServer.shortEngineer.getPrepopulate_id() + "");
        prepopulate.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                if (o.toString().equals("")) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.modify_enter_null_error), Toast.LENGTH_SHORT).show();
                    return false;
                } else {
                    EngineerServer.shortEngineer.setPrepopulate_id(Integer.parseInt(o.toString()));
                    setIdChangeToShow();
                    return true;
                }
            }
        });
        setShowChangeToSetId();
    }

    private void setShowChangeToSetId(){
        if (EngineerServer.shortEngineer.isShow_in_default_list()){
            EngineerServer.shortEngineer.setPrepopulate_id(EngineerServer.getPopulatedIdNotUsed());
        }else {
            EngineerServer.shortEngineer.setPrepopulate_id(0);
        }
        prepopulate.setText(EngineerServer.shortEngineer.getPrepopulate_id()+"");
    }
    private void setIdChangeToShow(){
        if (EngineerServer.shortEngineer.getPrepopulate_id()>0){
            EngineerServer.shortEngineer.setShow_in_default_list(true);
            show.setChecked(true);
        }else {
            EngineerServer.shortEngineer.setShow_in_default_list(false);
            show.setChecked(false);
        }
    }
}
