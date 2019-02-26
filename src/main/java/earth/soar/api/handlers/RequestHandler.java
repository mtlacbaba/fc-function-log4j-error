package earth.soar.api.handlers;

import com.aliyun.fc.runtime.Context;
import com.aliyun.fc.runtime.Credentials;
import com.aliyun.fc.runtime.StreamRequestHandler;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public class RequestHandler implements StreamRequestHandler {

    public RequestHandler(){
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        Map<String, String> variables = System.getenv();
        String env = variables.get("ENV");
        String fileName = "config." + env + ".json";
        context.getLogger().info(fileName);
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream stream = classLoader.getResourceAsStream(fileName);
        String json = IOUtils.toString(stream, "utf-8");
        context.getLogger().info(json);
        JSONObject obj = new JSONObject(json);
        context.getLogger().info(obj.toString());
        Config config = new Config(obj);
        JSONObject resObj = new JSONObject(config);
        context.getLogger().info(resObj.toString());
        writeResponse200(resObj, outputStream);
    }

    private void writeResponse200(JSONObject body, OutputStream stream) throws IOException {
        JSONObject res = new JSONObject();
        res.put("statusCode", 200);
        res.put("body", body);
        stream.write(res.toString().getBytes());
    }

}
