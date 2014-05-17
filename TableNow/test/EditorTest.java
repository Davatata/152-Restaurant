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
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.Button;

import com.tablenow.Editor;
import com.tablenow.MainActivity;
import com.tablenow.R;
import com.tablenow.Sign_up;
import com.tablenow.User;



@RunWith(RobolectricTestRunner.class)
public class EditorTest {
	
	@Test
	  public void onCreatTest() throws Exception {
	    Editor activity = Robolectric.buildActivity(Editor.class).create().get();
	    Bundle savedInstanceState = null;
		String url="http://50.161.166.152/restaurant/pages/restaurant.html";
		WebView wView = null;
		wView.loadUrl(url);
	    assertThat(true, equalTo(true));  
	  }
	
	@Test
	  public void onKeyDownTest() throws Exception {
		Editor activity = Robolectric.buildActivity(Editor.class).create().get();
		int key=4534;
		KeyEvent keyE = null;
		activity.onKeyDown(key, keyE);
	    assertThat(keyE, equalTo(null));  
		
	}
		
	
}
