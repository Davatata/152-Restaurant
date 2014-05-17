
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;
import org.robolectric.tester.android.view.TestMenuItem;

import static org.junit.Assert.assertThat;
//import static org.robolectric.Robolectric.shadowOf;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Button;

import com.tablenow.Editor;
import com.tablenow.MainActivity;
import com.tablenow.R;
import com.tablenow.Restaurant_update;
import com.tablenow.Sign_up;
import com.tablenow.User;
import com.tablenow.User_update;



@RunWith(RobolectricTestRunner.class)
public class User_updateTest {
	
	@Test
	  public void onCreatTest() throws Exception {
		Robolectric.buildActivity(Editor.class).create().get();
		Bundle savedInstanceState = null;
	    int test=(R.layout.activity_user_update);
	    assertThat(test, equalTo(0));  
	  }
	
	@Test
	  public void onCreateOptionMenuTest() throws Exception {
		Robolectric.buildActivity(Restaurant_update.class).create().get();
	    Menu menu=null;
	    ((ShadowActivity) menu).getMenuInflater().inflate(R.menu.user_update, menu);
	    assertThat(menu, equalTo(null));
	  }
	
	@Test
	  public void onOptionsItemSelectedTest() throws Exception {
		User_update activity=Robolectric.buildActivity(User_update.class).create().get();
	    MenuItem item = new TestMenuItem() {
		  public int getItemId() {
		    return R.id.action_sign_out;
		  }
		};

		activity.onOptionsItemSelected(item);

		ShadowActivity shadowActivity = Robolectric.shadowOf(activity);
		Intent startedIntent = shadowActivity.getNextStartedActivity();
		ShadowIntent shadowIntent = Robolectric.shadowOf(startedIntent);

		assertThat(shadowIntent.getComponent().getClassName(), equalTo(User_update.class.getName()));
	  }
}