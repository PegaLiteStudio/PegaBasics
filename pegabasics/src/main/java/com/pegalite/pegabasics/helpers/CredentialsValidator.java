package com.pegalite.pegabasics.helpers;

import android.app.Activity;

import com.pegalite.pegabasics.viewmanagers.PegaAnimationManager;

import java.util.regex.Pattern;

/**
 * Created by Sahil The Geek
 * Date : 24-02-2023.
 *
 * <p>This {@link CredentialsValidator} Class is Used to Validate user Credentials such as email, phone, password.</p>
 */
public class CredentialsValidator {

    // Constants for validation rules
    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final int MAX_PASSWORD_LENGTH = 30;
    public static final int MIN_NAME_LENGTH = 3;
    public static final int MAX_NAME_LENGTH = 30;
    public static final int MAX_EMAIL_LENGTH = 70;
    public static final int NUMBER_LENGTH = 10;

    // Email pattern (Allowing only Gmail addresses)
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@gmail\\.com$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    private final Activity context;

    /**
     * Constructor
     *
     * @param context Application or Activity context
     */
    public CredentialsValidator(Activity context) {
        this.context = context;
    }

    /**
     * Validates an email address.
     *
     * @param email     The email address to validate.
     * @param showToast Whether to show a toast message on failure.
     * @return true if the email is valid, false otherwise.
     */
    public boolean validateEmail(String email, boolean showToast) {
        if (email.length() > MAX_EMAIL_LENGTH) {
            if (showToast) {
                PegaAnimationManager.showToast(context, "Email can contain a maximum of " + MAX_EMAIL_LENGTH + " characters.");
            }
            return false;
        }

        boolean isValid = pattern.matcher(email).matches();
        if (!isValid && showToast) {
            PegaAnimationManager.showToast(context, "Invalid Email Address");
        }

        return isValid;
    }

    /**
     * Validates a password based on length and restrictions.
     *
     * @param password  The password to validate.
     * @param showToast Whether to show a toast message on failure.
     * @return true if the password is valid, false otherwise.
     */
    public boolean validatePassword(String password, boolean showToast) {
        if (password.length() < MIN_PASSWORD_LENGTH) {
            if (showToast) {
                PegaAnimationManager.showToast(context, "Password must have at least " + MIN_PASSWORD_LENGTH + " characters.");
            }
            return false;
        }
        if (password.length() > MAX_PASSWORD_LENGTH) {
            if (showToast) {
                PegaAnimationManager.showToast(context, "Password can contain a maximum of " + MAX_PASSWORD_LENGTH + " characters.");
            }
            return false;
        }
        if (password.contains(" ")) {
            if (showToast) {
                PegaAnimationManager.showToast(context, "Password cannot contain spaces.");
            }
            return false;
        }
        return true;
    }

    /**
     * Validates a phone number based on length.
     *
     * @param number    The phone number to validate.
     * @param showToast Whether to show a toast message on failure.
     * @return true if the number is valid, false otherwise.
     */
    public boolean validateNumber(String number, boolean showToast) {
        if (number.length() != NUMBER_LENGTH) {
            if (showToast) {
                PegaAnimationManager.showToast(context, "Please enter a valid phone number.");
            }
            return false;
        }
        return true;
    }

    /**
     * Validates a name based on length.
     *
     * @param name      The name to validate.
     * @param showToast Whether to show a toast message on failure.
     * @return true if the name is valid, false otherwise.
     */
    public boolean validateName(String name, boolean showToast) {
        if (name.length() < MIN_NAME_LENGTH) {
            if (showToast) {
                PegaAnimationManager.showToast(context, "Please enter a valid name.");
            }
            return false;
        }
        if (name.length() > MAX_NAME_LENGTH) {
            if (showToast) {
                PegaAnimationManager.showToast(context, "Name can contain a maximum of " + MAX_NAME_LENGTH + " characters.");
            }
            return false;
        }
        return true;
    }
}
