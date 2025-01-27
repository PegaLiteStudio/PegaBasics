package com.pegalite.pegabasics.yoyo.specials.in;

import android.animation.ObjectAnimator;
import android.view.View;

import com.daimajia.easing.Glider;
import com.daimajia.easing.Skill;
import com.pegalite.pegabasics.yoyo.BaseViewAnimator;

public class LandingAnimator extends BaseViewAnimator {
    @Override
    protected void prepare(View target) {
        getAnimatorAgent().playTogether(
                Glider.glide(Skill.QuintEaseOut, getDuration(), ObjectAnimator.ofFloat(target, "scaleX", 1.5f, 1f)),
                Glider.glide(Skill.QuintEaseOut, getDuration(), ObjectAnimator.ofFloat(target, "scaleY", 1.5f, 1f)),
                Glider.glide(Skill.QuintEaseOut, getDuration(), ObjectAnimator.ofFloat(target, "alpha", 0, 1f))
        );
    }
}
