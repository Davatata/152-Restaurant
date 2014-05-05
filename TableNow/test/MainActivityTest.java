import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;
import static org.junit.Assert.assertThat;
//import static org.robolectric.Robolectric.shadowOf;

import android.content.Intent;
import android.widget.Button;

import com.tablenow.MainActivity;
import com.tablenow.R;
import com.tablenow.Sign_up;



@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
	
	private MainActivity activity;
	private Button pressMeButton;
	
	@Before
	public void setUp() {
        activity = new MainActivity();
        activity.onCreate(null);

        pressMeButton = (Button) activity.findViewById(R.id.signUpButton);
  
    }
	
	@Test
	 public void should_start_result_activity_when_pluss_button_is_clicked() {
		pressMeButton.performClick();

        ShadowActivity shadowActivity = Robolectric.shadowOf(activity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = Robolectric.shadowOf(startedIntent);

        assertThat(shadowIntent.getComponent().getClassName(), equalTo(Sign_up.class.getName()));
    }


}