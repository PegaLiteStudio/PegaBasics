package com.pegalite.pegabasics.helpers;

import android.app.Activity;

import com.pegalite.pegabasics.viewmanagers.PegaAnimationManager;

import java.util.regex.Pattern;

/**
 * Created by Sahil The Geek
 * Date : 24-02-2023.
 *
 * <p>This {@link CredentialsValidator} Class is Used to Validate user Credentials such as email, phone, password </p>
 */
public class CredentialsValidator {

    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final int MAX_PASSWORD_LENGTH = 30;
    public static final int NUMBER_LENGTH = 11;
    public static final int MIN_NAME_LENGTH = 3;
    public static final int MAX_NAME_LENGTH = 30;
    public static final int MAX_EMAIL_LENGTH = 70;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@gmail.com";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    Activity activity;

    public CredentialsValidator(Activity activity) {
        this.activity = activity;
    }

    public boolean validateEmail(String email, boolean showToast) {
        if (email.length() > MAX_EMAIL_LENGTH) {
            if (showToast) {
                PegaAnimationManager.showToast(activity, "Email can Contain Maximum " + MAX_EMAIL_LENGTH + " Characters");
            }
            return false;
        }

        boolean b = pattern.matcher(email).matches();

        if (!b && showToast) {
            PegaAnimationManager.showToast(activity, "Invalid Email Address");
        }

        return b;
    }

    public boolean validatePassword(String password, boolean showToast) {
        if (password.length() < MIN_PASSWORD_LENGTH) {
            if (showToast) {
                PegaAnimationManager.showToast(activity, "Password must have minimum " + MIN_PASSWORD_LENGTH + " characters");
            }
            return false;
        }
        if (password.length() > MAX_PASSWORD_LENGTH) {
            if (showToast) {
                PegaAnimationManager.showToast(activity, "Password can Contain Maximum " + MAX_PASSWORD_LENGTH + " Characters");
            }
            return false;
        }
        if (password.contains(" ")) {
            if (showToast) {
                PegaAnimationManager.showToast(activity, "Password cannot contain space");
            }
            return false;
        }
        return true;
    }

    public boolean validateNumber(String number, boolean showToast) {
        if (number.length() != NUMBER_LENGTH) {
            if (showToast) {
                PegaAnimationManager.showToast(activity, "Please Enter a Valid Number");
            }
            return false;
        }

        return true;
    }

    public boolean validateName(String name, boolean showToast) {
        if (name.length() < MIN_NAME_LENGTH) {
            if (showToast) {
                PegaAnimationManager.showToast(activity, "Please Enter a Valid Name");
            }
            return false;
        }
        if (name.length() > MAX_NAME_LENGTH) {
            if (showToast) {
                PegaAnimationManager.showToast(activity, "Name can Contain Maximum " + MAX_NAME_LENGTH + " Characters");
            }
            return false;
        }
        return true;
    }

}
