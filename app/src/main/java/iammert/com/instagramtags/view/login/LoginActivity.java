package iammert.com.instagramtags.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import iammert.com.instagramtags.R;
import iammert.com.instagramtags.util.Constants;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class LoginActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        WebView webView = (WebView)findViewById(R.id.webview);
        webView.setWebViewClient(client);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl(Constants.LOGIN_URL);
    }

    private WebViewClient client = new WebViewClient(){
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(url.contains("access_token")){
                Intent intent = new Intent();
                intent.putExtra(Constants.KEY_INTENT_TOKEN, getAccessTokenFromURL(url));
                setResult(RESULT_OK, intent);
                finish();
            }
            return false;
        }
    };

    private String getAccessTokenFromURL(String url){
        int index = url.indexOf("access_token=");
        return url.substring(index + "access_token=".length());
    }
}
