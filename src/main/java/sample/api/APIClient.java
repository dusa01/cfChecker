package sample.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpException;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.http.impl.client.CloseableHttpClient;

public class APIClient<T, R> {

    /* ロガー */
    private static final Logger log = LoggerFactory.getLogger(APIClient.class);
    /* JSON文字列変換用マッパー */
    private static ObjectMapper mapper;

    /* staticイニシャライザ */
    static {
        mapper = new ObjectMapper();
    }

    public enum METHOD {
        POST,
        GET,
        PUT,
        ANY
    }

    /*
     * HTTP経由でAPIを実行する
     * JSONパラメータがあればrequest bodyに展開する
     */
    public synchronized T requestApi(TypeReference<T> type, R request, String url, METHOD method) throws Exception {
        if (request == null) {
            throw new IllegalArgumentException();
        }
        String responseJson = null;
        try (CloseableHttpClient client = HttpClients.createSystem()) {
            switch (method) {
                case POST:
                    HttpPost post = new HttpPost(url);
                    StringEntity params = new StringEntity(mapper.writeValueAsString(request), "UTF-8");
                    post.setHeader("Content-Type", "application/json");
                    post.setEntity(params);
                    CloseableHttpResponse res = client.execute(post);
                    if (res.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                        throw new HttpException("Http response status is not 200");
                    }
                    responseJson = EntityUtils.toString(res.getEntity(), "UTF-8");
                    return mapper.readValue(responseJson, type);
                case GET:
                case PUT:
                case ANY:
                    throw new UnsupportedOperationException();
                default:
                    throw new UnsupportedOperationException();
            }
        } catch (ParseException pe) {
            ResponseError err = mapper.readValue(responseJson, ResponseError.class);
            throw new AWSLambdaApiException(err);
        }

    }
}
