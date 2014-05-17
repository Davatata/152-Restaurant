import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowEnvironment;
import org.robolectric.shadows.ShadowIntent;
import org.robolectric.tester.android.view.TestMenuItem;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
//import static org.robolectric.Robolectric.shadowOf;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.tablenow.Editor;
import com.tablenow.MainActivity;
import com.tablenow.R;
import com.tablenow.R.menu;
import com.tablenow.Sign_up;
import com.tablenow.User;



@RunWith(RobolectricTestRunner.class)
public class Sign_upTest {
	
	@SuppressWarnings("null")
	@Test
	  public void onCreateOptionMenuTest() throws Exception {
	    Sign_up activity = Robolectric.buildActivity(Sign_up.class).create().get();
	    Menu menu=null;
	    ((ShadowActivity) menu).getMenuInflater().inflate(R.menu.sign_up, menu);
	    assertThat(menu, equalTo(null));
	  }
	
	@Test
	  public void UserCheckTest() throws Exception {
	    Sign_up activity = Robolectric.buildActivity(Sign_up.class).create().get();
	    View view = null;
	    activity.check_res(view);
	    @SuppressWarnings("null")
		boolean cs = ((CheckBox) view).isChecked();
	    assertEquals(false, cs);
	  }
	
	@Test
	  public void RESCheckTest() throws Exception {
	    Sign_up activity = Robolectric.buildActivity(Sign_up.class).create().get();
	    View view = null;
	    activity.check_res(view);
	    @SuppressWarnings("null")
		boolean ch = ((CheckBox) view).isChecked();
	    assertEquals(false, ch);
	  }
	
	@Test
	  public void MenuItemCheck() throws Exception {
		Sign_up activity=Robolectric.buildActivity(Sign_up.class).create().get();
	    MenuItem item = new TestMenuItem() {
		  public int getItemId() {
		    return R.id.search_button;
		  }
		};

		activity.onOptionsItemSelected(item);

		ShadowActivity shadowActivity = Robolectric.shadowOf(activity);
		Intent startedIntent = shadowActivity.getNextStartedActivity();
		ShadowIntent shadowIntent = Robolectric.shadowOf(startedIntent);

		assertThat(shadowIntent.getComponent().getClassName(), equalTo(Sign_up.class.getName()));
	  }
	
	
	
}