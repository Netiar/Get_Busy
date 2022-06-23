package com.moringaschool.get_busy;

import static org.junit.Assert.assertEquals;

import android.app.Activity;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.moringaschool.get_busy.ui.LoginActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class LoginActivityUnitTest {
    Activity act;

    @Before
    public void setUp(){
        act = Robolectric.buildActivity(LoginActivity.class).create().start().resume().get();

    }

    @Test
    public void checkLogoText(){
        String logo = "Get Busy";
        TextView logoView = act.findViewById(R.id.logWelcome);


        assertEquals(logo, logoView.getText().toString() );
    }

    @Test
    public void checkSloganText(){
        String logo = "Don't have an account??";
        TextView logoView = act.findViewById(R.id.quest);


        assertEquals(logo, logoView.getText().toString() );
    }

    @Test
    public void checkNotAMemberText(){
        String logo = "Don't get bored! Get onboard. Get Busy!";
        TextView logoView = act.findViewById(R.id.logSlogan);


        assertEquals(logo, logoView.getText().toString() );
    }
    @Test
    public void checkButtonText(){
        String logo = "login";
        MaterialButton logoView = act.findViewById(R.id.btnLogin);

        assertEquals(logo, logoView.getText().toString() );
    }
    @Test
    public void checkSignInButtonText(){
        String logo = "SignUp";
        MaterialButton logoView = act.findViewById(R.id.btnSign);

        assertEquals(logo, logoView.getText().toString() );
    }
    @Test
    public void checkCheckBoxText(){
        String logo = "Remember me";
        CheckBox logoView = act.findViewById(R.id.checkBox);

        assertEquals(logo, logoView.getText().toString() );
    }

    @Test
    public void checkEditTextHints(){
        TextInputLayout logoView1 = act.findViewById(R.id.userName);
        TextInputLayout logoView2 = act.findViewById(R.id.userPhone);


        assertEquals("Enter your Email Address", logoView1.getEditText().getHint().toString() );
        assertEquals("Enter your password", logoView2.getEditText().getHint().toString() );

    }

}
