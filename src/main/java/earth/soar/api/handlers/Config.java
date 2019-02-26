package earth.soar.api.handlers;

import org.json.JSONObject;

public class Config {

    public final String config1;
    public final String config2;

    public Config(JSONObject obj) {
        config1 = obj.optString("config1", null);
        config2 = obj.optString("config2", null);
    }

    public String getConfig1() {
        return config1;
    }

    public String getConfig2() {
        return config2;
    }
}
