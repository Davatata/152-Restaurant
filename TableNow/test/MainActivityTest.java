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
import android.view.Menu;
import android.widget.Button;

import com.tablenow.Editor;
import com.tablenow.MainActivity;
import com.tablenow.R;
import com.tablenow.Sign_up;
import com.tablenow.User;



@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
	
	@Test
	  public void onCreateOptionMenuTest() throws Exception {
	    MainActivity activity = Robolectric.buildActivity(MainActivity.class).create().get();
	    Menu menu=null;
	    boolean result= activity.onCreateOptionsMenu(menu);
	    assertThat(result, equalTo(true));
	  }
	 
	 @Test
	  public void loginButtonTest() throws Exception {
	    MainActivity activity = Robolectric.buildActivity(MainActivity.class).create().get();

	    Button pressMeButton = (Button) activity.findViewById(R.id.logInButton);

	    pressMeButton.performClick();
	    
	    ShadowActivity shadowActivity = Robolectric.shadowOf(activity);
   Intent startedIntent = shadowActivity.getNextStartedActivity();
   ShadowIntent shadowIntent = Robolectric.shadowOf(startedIntent);
	    assertThat(shadowIntent.getComponent().getClassName(), equalTo(MainActivity.class.getName()));
	  }

	  @Test
	  public void clickingButton_shouldChangeToSigUpActivity() throws Exception {
	    MainActivity activity = Robolectric.buildActivity(MainActivity.class).create().get();

	    Button pressMeButton = (Button) activity.findViewById(R.id.signUpButton);

	    pressMeButton.performClick();
	    
	    ShadowActivity shadowActivity = Robolectric.shadowOf(activity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = Robolectric.shadowOf(startedIntent);
	    assertThat(shadowIntent.getComponent().getClassName(), equalTo(Sign_up.class.getName()));
	  }
	  
	  @Test
	  public void clickingButton_shouldChangeEditorActivity() throws Exception {
	    MainActivity activity = Robolectric.buildActivity(MainActivity.class).create().get();

	    Button pressMeRestaurantButton = (Button) activity.findViewById(R.id.Restaurant);

	    pressMeRestaurantButton.performClick();
	    
	    ShadowActivity shadowActivity = Robolectric.shadowOf(activity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = Robolectric.shadowOf(startedIntent);
	    assertThat(shadowIntent.getComponent().getClassName(), equalTo(Editor.class.getName()));
	  }
	  
	  @Test
	  public void clickingButton_shouldChangeUserActivity() throws Exception {
	    MainActivity activity = Robolectric.buildActivity(MainActivity.class).create().get();

	    Button pressMeUserButton = (Button) activity.findViewById(R.id.User);

	    pressMeUserButton.performClick();
	    
	    ShadowActivity shadowActivity = Robolectric.shadowOf(activity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = Robolectric.shadowOf(startedIntent);
	    assertThat(shadowIntent.getComponent().getClassName(), equalTo(User.class.getName()));
	  }
	  
	  
}