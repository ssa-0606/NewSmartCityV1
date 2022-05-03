package com.imikasa.ui.notifications.tool;

import android.graphics.Canvas;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class MyRender extends XAxisRenderer {
    public MyRender(ViewPortHandler viewPortHandler, XAxis xAxis, Transformer trans) {
        super(viewPortHandler, xAxis, trans);
    }

    @Override
    protected void drawLabel(Canvas c, String formattedLabel, float x, float y, MPPointF anchor, float angleDegrees) {
        String[] split = formattedLabel.split("\n");
        for (int i = 0; i < split.length; i++) {
            float value = i*mAxisLabelPaint.getTextSize();
            Utils.drawXAxisValue(c,split[i],x,y+value,mAxisLabelPaint,anchor,angleDegrees);
        }
    }
}
