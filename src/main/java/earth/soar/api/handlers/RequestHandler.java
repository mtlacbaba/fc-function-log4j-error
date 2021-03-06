package earth.soar.api.handlers;

import com.aliyun.fc.runtime.Context;
import com.aliyun.fc.runtime.Credentials;
import com.aliyun.fc.runtime.StreamRequestHandler;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RequestHandler implements StreamRequestHandler {

    public RequestHandler(){
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        Credentials c = context.getExecutionCredentials();
        DefaultProfile.addEndpoint("ap-southeast-1", "Dm", "dm.ap-southeast-1.aliyuncs.com");
        IClientProfile profile = DefaultProfile.getProfile("ap-southeast-1", c.getAccessKeyId(), c.getAccessKeySecret(), c.getSecurityToken());
        DefaultAcsClient client = new DefaultAcsClient(profile);
        DefaultProfile p = client.getProfile();
        JSONObject resObj = new JSONObject(p);
        writeResponse200(resObj, outputStream);
    }

    private void writeResponse200(JSONObject body, OutputStream stream) throws IOException {
        JSONObject res = new JSONObject();
        res.put("statusCode", 200);
        res.put("body", body);
        stream.write(res.toString().getBytes());
    }

    protected void writeErrorResponse(Exception e, OutputStream outputStream) throws IOException {
        writeResponse400(e.getMessage(), outputStream);
    }

    private void writeResponse400(String message, OutputStream stream) throws IOException {
        JSONObject body = new JSONObject();
        body.put("error", message);
        JSONObject res = new JSONObject();
        res.put("statusCode", 400);
        res.put("body", body);
        stream.write(res.toString().getBytes());
    }

    private void writeResponse401(OutputStream stream) throws IOException {
        JSONObject res = new JSONObject();
        JSONObject body = new JSONObject();
        body.put("error", "Incorrect email or password");
        res.put("statusCode", 401);
        res.put("body", body);
        stream.write(res.toString().getBytes());
    }


}
