package com.totrit.androidsimpletest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * TODO: document your custom view class.
 */
public class CommonControlsViewGroup extends RelativeLayout {
    private static int sPreviouslySelectedActivity = 0;

    public CommonControlsViewGroup(Context context) {
        super(context);
    }

    public CommonControlsViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonControlsViewGroup(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate () {
        initPanel();
        registerListeners();
    }

    private void initPanel() {
        RadioGroup activities = (RadioGroup)CommonControlsViewGroup.this.findViewById(R.id.activities);
        activities.check(sPreviouslySelectedActivity);

        TextView display = (TextView)CommonControlsViewGroup.this.findViewById(R.id.display);
        display.setText("task=" + ((Activity)this.getContext()).getTaskId() + ", activity-id=" + this.getContext().hashCode());
    }

    private void registerListeners() {
        Button go = (Button)this.findViewById(R.id.go);
        go.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioGroup activities = (RadioGroup)CommonControlsViewGroup.this.findViewById(R.id.activities);
                sPreviouslySelectedActivity = activities.getCheckedRadioButtonId();
                if (sPreviouslySelectedActivity == -1) {
                    return;
                }
                Class<?> targetActivity = null;
                switch (sPreviouslySelectedActivity) {
                    case R.id.radio_act_1:
                        targetActivity = MainActivity1.class;
                        break;
                    case R.id.radio_act_2:
                        targetActivity = MainActivity2.class;
                        break;
                    case R.id.radio_act_3:
                        targetActivity = MainActivity3.class;
                        break;
                    case R.id.radio_act_4:
                        targetActivity = MainActivity4.class;
                        break;
                }
                Intent intent = new Intent();
                intent.setClass(CommonControlsViewGroup.this.getContext(), targetActivity);
                CommonControlsViewGroup.this.getContext().startActivity(intent);
            }
        });
    }
}
