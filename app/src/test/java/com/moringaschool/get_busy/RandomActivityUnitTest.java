package com.moringaschool.get_busy;

import static org.junit.Assert.assertEquals;

import android.app.Activity;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.moringaschool.get_busy.ui.RandomActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class RandomActivityUnitTest {
    Activity act;

    @Before
    public void setUp(){
        act = Robolectric.buildActivity(RandomActivity.class).create().start().resume().get();

    }

    @Test
    public void checkTextToDisplayActivityType(){
        TextView activityText = act.findViewById(R.id.options_display);
        String text = "";

        assertEquals(text, activityText.getText().toString());
    }
    @Test
    public void checkTextToDisplayActivityDescription(){
        TextView activityText = act.findViewById(R.id.textViewTextMultiLine);
        String text = "";

        assertEquals(text, activityText.getText().toString());
    }

    @Test
    public void checkTextOfFloatingButtonAddToFavourites(){
        ExtendedFloatingActionButton activityText = act.findViewById(R.id.like);
        String text = "";

        assertEquals(text, activityText.getText().toString());
    }
}
