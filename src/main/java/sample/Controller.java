package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.fasterxml.jackson.core.type.TypeReference;
import sample.api.impl.CloudFrontCookie;
import sample.api.impl.GetCloudFrontCookie;
import sample.download.SimpleContentDownloader;

import java.net.URL;


public class Controller {
    @FXML
    private TextField url;
    @FXML
    private TextField contents;
    @FXML
    private Label result;

    private final String GetCloudFrontCookieUrl = "http://ec2-52-198-120-40.ap-northeast-1.compute.amazonaws.com:8080/getCookie";
    private String policy, target;
    //    String ContentUrl = "http://d3pycss8turl1w.cloudfront.net/game/keys/iOS.jpg";
    String ContentUrl = "https://d3pycss8turl1w.cloudfront.net/product/chikuchiku2019/shadowverse/content/win/1.0.1/data/android-icon-36x36.png";

    @FXML
    protected void OnCheckButtonClick(ActionEvent event) {
        policy = url.getText();
        target = contents.getText();

        try {
            //Cookie取得
            CloudFrontCookie cookie = (CloudFrontCookie) new GetCloudFrontCookie(GetCloudFrontCookieUrl, new TypeReference<CloudFrontCookie>() {
            }, policy).execHttpApi();
            String SetCookie = "CloudFront-Policy=" + cookie.getPolicy() + ";CloudFront-Signature=" + cookie.getSignature() + ";CloudFront-Key-Pair-Id=" + cookie.getKey();

            //取得後DL
            URL url = new URL(target);
            SimpleContentDownloader dl = new SimpleContentDownloader(url, SetCookie, "C:\\temp\\test.jpeg", 0);

            if(dl.download()){
                result.setText("Check OK!!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
