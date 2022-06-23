package com.moringaschool.get_busy;

import static org.junit.Assert.assertEquals;

import android.app.Activity;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.moringaschool.get_busy.ui.RegisterActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class RegisterActivityTest {
    Activity act;

    @Before
    public void setUp(){
        act = Robolectric.buildActivity(RegisterActivity.class).create().start().resume().get();
    }

    @Test
    public void checkLogoText(){
        String logo = "Get Busy";
        TextView logoView = act.findViewById(R.id.welcomeText);


        assertEquals(logo, logoView.getText().toString() );
    }

    @Test
    public void checkSloganText(){
        String logo = "Please Fill in your details too access our random activities";
        TextView logoView = act.findViewById(R.id.fill);


        assertEquals(logo, logoView.getText().toString() );
    }
    @Test
    public void checkButtonText(){
        String logo = "Submit";
        MaterialButton logoView = act.findViewById(R.id.btnSubmit);

        assertEquals(logo, logoView.getText().toString() );
    }

    @Test
    public void checkEditTextHints(){
        TextInputLayout logoView1 = act.findViewById(R.id.userName);
        TextInputLayout logoView2 = act.findViewById(R.id.userPhone);
        TextInputLayout logoView3 = act.findViewById(R.id.userTicket);
        TextInputLayout logoView4 = act.findViewById(R.id.userSeat);
        TextInputLayout logoView5 = act.findViewById(R.id.userCoach);

        assertEquals("Enter your full name", logoView1.getEditText().getHint().toString() );
        assertEquals("Enter you mobile number", logoView2.getEditText().getHint().toString() );
        assertEquals("Enter your email address", logoView3.getEditText().getHint().toString() );
        assertEquals("Enter your password", logoView4.getEditText().getHint().toString() );
        assertEquals("Confirm your password", logoView5.getEditText().getHint().toString() );
    }

}
