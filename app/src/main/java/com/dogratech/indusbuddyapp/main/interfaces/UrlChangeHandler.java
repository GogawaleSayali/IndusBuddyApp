package com.dogratech.indusbuddyapp.main.interfaces;

/**
 * Created by jboggess on 9/14/16.
 */
public interface UrlChangeHandler {
    void onUrlChanged(String newUrl);
    void onLoadError(int errorCode, CharSequence description);
}
