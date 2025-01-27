package com.pegalite.pegabasics.viewmanagers;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pegalite.pegabasics.yoyo.Techniques;
import com.pegalite.pegabasics.yoyo.YoYo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Sahil The Geek
 * Date : 13-05-2023.
 *
 * <p>This {@link PegaAnimationManager} helps a view to animate like expanding</p>
 */
public class PegaAnimationManager {

    /**
     * This method will shake the view for once
     */
    public static void shake(View v) {
        if (v != null) {
            YoYo.with(Techniques.Shake).duration(1000).repeat(0).playOn(v);
        }
    }

    public static void showToast(Activity context, String msg) {
        if (!context.isFinishing()) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }

    public static void slideUpAndHide(final View view) {
        // Slide the view up and make it INVISIBLE
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", 0, -view.getHeight());
        animator.setDuration(300); // Animation duration in milliseconds

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE); // Set the view to INVISIBLE after the animation ends
            }
        });

        animator.start(); // Start the animation
    }

    public static void slideDownAndShow(final View view) {
        // Make the view visible before starting the animation
        view.setVisibility(View.VISIBLE);
        view.setTranslationY(-view.getHeight()); // Set the starting position (off-screen)

        // Slide the view down to its original position
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", -view.getHeight(), 0);
        animator.setDuration(300); // Animation duration in milliseconds

        animator.start(); // Start the animation
    }

    /**
     * Animates all child views of a parent container to fade in and translate up.
     * Excluded views remain unchanged.
     *
     * @param container the parent view group containing child views to animate.
     * @param exclude   a list of view IDs to exclude from the animation.
     * @param activity  the activity context to run UI thread operations.
     */
    public static void fadeUp(ViewGroup container, List<Integer> exclude, Activity activity) {
        Thread thread = new Thread(() -> {
            int childCount = container.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View view = container.getChildAt(i);
                if (!exclude.contains(view.getId())) {
                    view.setTranslationY(100f);
                    view.setAlpha(0f);
                }
            }

            for (int i = 0; i < childCount; i++) {
                View view = container.getChildAt(i);
                if (!exclude.contains(view.getId())) {
                    try {
                        Thread.sleep(50);
                        activity.runOnUiThread(() -> view.animate().translationY(0f).alpha(1f).setDuration(500).start());
                    } catch (InterruptedException e) {
                        Logger.getLogger("PegaAnimationUtils fadeUp").log(Level.WARNING, e.getMessage(), e);
                    }
                }
            }

        });
        thread.start();
    }

}
