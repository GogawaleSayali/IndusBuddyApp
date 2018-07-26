package com.dogratech.indusbuddyapp.main.activities.fitness.fitbitintegration;

/**
 * Created by jboggess on 9/19/16.
 */
public interface LogoutTaskCompletionHandler {
    void logoutSuccess();

    void logoutError(String message);
}
