package earth.soar.api.handlers;
import com.aliyun.fc.runtime.Context;
import com.aliyun.fc.runtime.Credentials;
import com.aliyun.fc.runtime.FunctionComputeLogger;
import com.aliyun.fc.runtime.FunctionParam;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class RequestHandlerTests {

    @Test
    public void handleRequest() throws IOException {
        RequestHandler handler = new RequestHandler();

        InputStream inputStream = new ByteArrayInputStream(new byte[0]);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        handler.handleRequest(inputStream, outputStream, new MockContext());
    }

    private static class MockCredentials implements Credentials {

        @Override
        public String getAccessKeyId() {
            return null;
        }

        @Override
        public String getAccessKeySecret() {
            return null;
        }

        @Override
        public String getSecurityToken() {
            return null;
        }
    }

    private static class MockContext implements Context {

        @Override
        public String getRequestId() {
            return null;
        }

        @Override
        public Credentials getExecutionCredentials() {
            return new MockCredentials();
        }

        @Override
        public FunctionParam getFunctionParam() {
            return null;
        }

        @Override
        public FunctionComputeLogger getLogger() {
            return null;
        }
    }
}
