package com.example.civilization_app;


import android.content.res.Resources;

import androidx.test.annotation.UiThreadTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.filters.SmallTest;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NavigationTest {

    private static final int[] DESTINATIONS = {R.id.navigation_civilizations, R.id.navigation_favorites, R.id.navigation_share};
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    private Map<Integer, String> menuContent;

    private BottomNavigationView bottomNavigationView;

    @Before
    public void setUp() throws Exception {
        final MainActivity mainActivity = mainActivityActivityTestRule.getActivity();
        mainActivity.setTheme(R.style.Theme_MaterialComponents_Light);
        bottomNavigationView = mainActivity.findViewById(R.id.navigation_view);

        final Resources res = mainActivity.getResources();
        menuContent = new HashMap<>(DESTINATIONS.length);
        menuContent.put(R.id.navigation_civilizations, res.getString(R.id.navigation_civilizations));
        menuContent.put(R.id.navigation_favorites, res.getString(R.id.navigation_favorites));
        menuContent.put(R.id.navigation_share, res.getString(R.id.navigation_share));
    }


    @UiThreadTest
    @Test
    @SmallTest
    public void testAddItemsWithoutMenuInflation() {
        BottomNavigationView bottomNavigationView = new BottomNavigationView(mainActivityActivityTestRule.getActivity());
        mainActivityActivityTestRule.getActivity().setContentView(bottomNavigationView);
        bottomNavigationView.getMenu().add("New item 1");
        bottomNavigationView.getMenu().add("New item 2");
        assertEquals(2, bottomNavigationView.getMenu().size());
        bottomNavigationView.getMenu().removeItem(0);
        bottomNavigationView.getMenu().removeItem(0);
        assertEquals(0, bottomNavigationView.getMenu().size());
    }
}
