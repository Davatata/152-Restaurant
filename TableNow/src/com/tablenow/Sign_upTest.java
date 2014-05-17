package com.tablenow;

import org.junit.Test;

import android.view.View;
import android.widget.CheckBox;

import junit.framework.TestCase;

public class Sign_upTest extends TestCase {
	
	@Test
	public void checkUserTest(View view){
		Sign_up activity = new Sign_up();
		activity.check_user(view);
		boolean c=((CheckBox) view).isChecked();;
		assertEquals(true, c);
	}

}
