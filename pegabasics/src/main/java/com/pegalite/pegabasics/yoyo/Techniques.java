
/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 daimajia
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.pegalite.pegabasics.yoyo;

import com.pegalite.pegabasics.yoyo.attention.BounceAnimator;
import com.pegalite.pegabasics.yoyo.attention.FlashAnimator;
import com.pegalite.pegabasics.yoyo.attention.PulseAnimator;
import com.pegalite.pegabasics.yoyo.attention.RubberBandAnimator;
import com.pegalite.pegabasics.yoyo.attention.ShakeAnimator;
import com.pegalite.pegabasics.yoyo.attention.StandUpAnimator;
import com.pegalite.pegabasics.yoyo.attention.SwingAnimator;
import com.pegalite.pegabasics.yoyo.attention.TadaAnimator;
import com.pegalite.pegabasics.yoyo.attention.WaveAnimator;
import com.pegalite.pegabasics.yoyo.attention.WobbleAnimator;
import com.pegalite.pegabasics.yoyo.bouncing_entrances.BounceInAnimator;
import com.pegalite.pegabasics.yoyo.bouncing_entrances.BounceInDownAnimator;
import com.pegalite.pegabasics.yoyo.bouncing_entrances.BounceInLeftAnimator;
import com.pegalite.pegabasics.yoyo.bouncing_entrances.BounceInRightAnimator;
import com.pegalite.pegabasics.yoyo.bouncing_entrances.BounceInUpAnimator;
import com.pegalite.pegabasics.yoyo.fading_entrances.FadeInAnimator;
import com.pegalite.pegabasics.yoyo.fading_entrances.FadeInDownAnimator;
import com.pegalite.pegabasics.yoyo.fading_entrances.FadeInLeftAnimator;
import com.pegalite.pegabasics.yoyo.fading_entrances.FadeInRightAnimator;
import com.pegalite.pegabasics.yoyo.fading_entrances.FadeInUpAnimator;
import com.pegalite.pegabasics.yoyo.fading_exits.FadeOutAnimator;
import com.pegalite.pegabasics.yoyo.fading_exits.FadeOutDownAnimator;
import com.pegalite.pegabasics.yoyo.fading_exits.FadeOutLeftAnimator;
import com.pegalite.pegabasics.yoyo.fading_exits.FadeOutRightAnimator;
import com.pegalite.pegabasics.yoyo.fading_exits.FadeOutUpAnimator;
import com.pegalite.pegabasics.yoyo.flippers.FlipInXAnimator;
import com.pegalite.pegabasics.yoyo.flippers.FlipInYAnimator;
import com.pegalite.pegabasics.yoyo.flippers.FlipOutXAnimator;
import com.pegalite.pegabasics.yoyo.flippers.FlipOutYAnimator;
import com.pegalite.pegabasics.yoyo.rotating_entrances.RotateInAnimator;
import com.pegalite.pegabasics.yoyo.rotating_entrances.RotateInDownLeftAnimator;
import com.pegalite.pegabasics.yoyo.rotating_entrances.RotateInDownRightAnimator;
import com.pegalite.pegabasics.yoyo.rotating_entrances.RotateInUpLeftAnimator;
import com.pegalite.pegabasics.yoyo.rotating_entrances.RotateInUpRightAnimator;
import com.pegalite.pegabasics.yoyo.rotating_exits.RotateOutAnimator;
import com.pegalite.pegabasics.yoyo.rotating_exits.RotateOutDownLeftAnimator;
import com.pegalite.pegabasics.yoyo.rotating_exits.RotateOutDownRightAnimator;
import com.pegalite.pegabasics.yoyo.rotating_exits.RotateOutUpLeftAnimator;
import com.pegalite.pegabasics.yoyo.rotating_exits.RotateOutUpRightAnimator;
import com.pegalite.pegabasics.yoyo.sliders.SlideInDownAnimator;
import com.pegalite.pegabasics.yoyo.sliders.SlideInLeftAnimator;
import com.pegalite.pegabasics.yoyo.sliders.SlideInRightAnimator;
import com.pegalite.pegabasics.yoyo.sliders.SlideInUpAnimator;
import com.pegalite.pegabasics.yoyo.sliders.SlideOutDownAnimator;
import com.pegalite.pegabasics.yoyo.sliders.SlideOutLeftAnimator;
import com.pegalite.pegabasics.yoyo.sliders.SlideOutRightAnimator;
import com.pegalite.pegabasics.yoyo.sliders.SlideOutUpAnimator;
import com.pegalite.pegabasics.yoyo.specials.HingeAnimator;
import com.pegalite.pegabasics.yoyo.specials.RollInAnimator;
import com.pegalite.pegabasics.yoyo.specials.RollOutAnimator;
import com.pegalite.pegabasics.yoyo.specials.in.DropOutAnimator;
import com.pegalite.pegabasics.yoyo.specials.in.LandingAnimator;
import com.pegalite.pegabasics.yoyo.specials.out.TakingOffAnimator;
import com.pegalite.pegabasics.yoyo.zooming_entrances.ZoomInAnimator;
import com.pegalite.pegabasics.yoyo.zooming_entrances.ZoomInDownAnimator;
import com.pegalite.pegabasics.yoyo.zooming_entrances.ZoomInLeftAnimator;
import com.pegalite.pegabasics.yoyo.zooming_entrances.ZoomInRightAnimator;
import com.pegalite.pegabasics.yoyo.zooming_entrances.ZoomInUpAnimator;
import com.pegalite.pegabasics.yoyo.zooming_exits.ZoomOutAnimator;
import com.pegalite.pegabasics.yoyo.zooming_exits.ZoomOutDownAnimator;
import com.pegalite.pegabasics.yoyo.zooming_exits.ZoomOutLeftAnimator;
import com.pegalite.pegabasics.yoyo.zooming_exits.ZoomOutRightAnimator;
import com.pegalite.pegabasics.yoyo.zooming_exits.ZoomOutUpAnimator;

public enum Techniques {

    DropOut(DropOutAnimator.class),
    Landing(LandingAnimator.class),
    TakingOff(TakingOffAnimator.class),

    Flash(FlashAnimator.class),
    Pulse(PulseAnimator.class),
    RubberBand(RubberBandAnimator.class),
    Shake(ShakeAnimator.class),
    Swing(SwingAnimator.class),
    Wobble(WobbleAnimator.class),
    Bounce(BounceAnimator.class),
    Tada(TadaAnimator.class),
    StandUp(StandUpAnimator.class),
    Wave(WaveAnimator.class),

    Hinge(HingeAnimator.class),
    RollIn(RollInAnimator.class),
    RollOut(RollOutAnimator.class),

    BounceIn(BounceInAnimator.class),
    BounceInDown(BounceInDownAnimator.class),
    BounceInLeft(BounceInLeftAnimator.class),
    BounceInRight(BounceInRightAnimator.class),
    BounceInUp(BounceInUpAnimator.class),

    FadeIn(FadeInAnimator.class),
    FadeInUp(FadeInUpAnimator.class),
    FadeInDown(FadeInDownAnimator.class),
    FadeInLeft(FadeInLeftAnimator.class),
    FadeInRight(FadeInRightAnimator.class),

    FadeOut(FadeOutAnimator.class),
    FadeOutDown(FadeOutDownAnimator.class),
    FadeOutLeft(FadeOutLeftAnimator.class),
    FadeOutRight(FadeOutRightAnimator.class),
    FadeOutUp(FadeOutUpAnimator.class),

    FlipInX(FlipInXAnimator.class),
    FlipOutX(FlipOutXAnimator.class),
    FlipInY(FlipInYAnimator.class),
    FlipOutY(FlipOutYAnimator.class),
    RotateIn(RotateInAnimator.class),
    RotateInDownLeft(RotateInDownLeftAnimator.class),
    RotateInDownRight(RotateInDownRightAnimator.class),
    RotateInUpLeft(RotateInUpLeftAnimator.class),
    RotateInUpRight(RotateInUpRightAnimator.class),

    RotateOut(RotateOutAnimator.class),
    RotateOutDownLeft(RotateOutDownLeftAnimator.class),
    RotateOutDownRight(RotateOutDownRightAnimator.class),
    RotateOutUpLeft(RotateOutUpLeftAnimator.class),
    RotateOutUpRight(RotateOutUpRightAnimator.class),

    SlideInLeft(SlideInLeftAnimator.class),
    SlideInRight(SlideInRightAnimator.class),
    SlideInUp(SlideInUpAnimator.class),
    SlideInDown(SlideInDownAnimator.class),

    SlideOutLeft(SlideOutLeftAnimator.class),
    SlideOutRight(SlideOutRightAnimator.class),
    SlideOutUp(SlideOutUpAnimator.class),
    SlideOutDown(SlideOutDownAnimator.class),

    ZoomIn(ZoomInAnimator.class),
    ZoomInDown(ZoomInDownAnimator.class),
    ZoomInLeft(ZoomInLeftAnimator.class),
    ZoomInRight(ZoomInRightAnimator.class),
    ZoomInUp(ZoomInUpAnimator.class),

    ZoomOut(ZoomOutAnimator.class),
    ZoomOutDown(ZoomOutDownAnimator.class),
    ZoomOutLeft(ZoomOutLeftAnimator.class),
    ZoomOutRight(ZoomOutRightAnimator.class),
    ZoomOutUp(ZoomOutUpAnimator.class);


    private Class animatorClazz;

    private Techniques(Class clazz) {
        animatorClazz = clazz;
    }

    public BaseViewAnimator getAnimator() {
        try {
            return (BaseViewAnimator) animatorClazz.newInstance();
        } catch (Exception e) {
            throw new Error("Can not init animatorClazz instance");
        }
    }
}
