package com.zelora.billing;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {
    private WebView webView;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        setContentView(webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowContentAccess(false);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.addJavascriptInterface(new NativeBridge(this), "ZeloraNative");
        webView.loadUrl("file:///android_asset/index.html");
    }

    private void printPage() {
        if (android.os.Build.VERSION.SDK_INT < 19) {
            Toast.makeText(this, "Printing requires Android 4.4 or later", Toast.LENGTH_LONG).show();
            return;
        }
        PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
        android.print.PrintDocumentAdapter adapter = webView.createPrintDocumentAdapter("ZELORA Receipt");
        PrintAttributes attributes = new PrintAttributes.Builder()
                .setMediaSize(PrintAttributes.MediaSize.UNKNOWN_PORTRAIT)
                .setResolution(new PrintAttributes.Resolution("zelora", "ZELORA", 203, 203))
                .setMinMargins(PrintAttributes.Margins.NO_MARGINS)
                .build();
        printManager.print("ZELORA Receipt", adapter, attributes);
    }

    private class NativeBridge {
        private final Activity activity;
        NativeBridge(Activity activity) { this.activity = activity; }
        @JavascriptInterface public void printReceipt() {
            activity.runOnUiThread(() -> printPage());
        }
    }

    @Override public void onBackPressed() {
        if (webView != null && webView.canGoBack()) webView.goBack();
        else super.onBackPressed();
    }
}
