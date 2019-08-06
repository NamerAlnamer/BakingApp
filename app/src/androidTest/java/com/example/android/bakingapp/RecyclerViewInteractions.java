package com.example.android.bakingapp;

import android.view.View;

import androidx.test.espresso.NoMatchingViewException;

import org.hamcrest.Matcher;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;


public class RecyclerViewInteractions <A>{

    private Matcher<View> viewMatcher;
    private List<A> items;

    private RecyclerViewInteractions(Matcher<View> viewMatcher) {
        this.viewMatcher = viewMatcher;
    }

    public static <A> RecyclerViewInteractions<A> onRecyclerView(Matcher<View> viewMatcher) {
        return new RecyclerViewInteractions<>(viewMatcher);
    }

    public RecyclerViewInteractions<A> withItems(List<A> items) {
        this.items = items;
        return this;
    }

    public RecyclerViewInteractions<A> check(ItemViewAssertion<A> itemViewAssertion) {
        for (int i = 0; i < items.size(); i++)
            onView(viewMatcher)
                    .perform(scrollToPosition(i))
                    .check(new RecyclerItemViewAssertions<A>(i, items.get(i), (ItemViewAssertion<Object>) itemViewAssertion));
        return this;
    }

    public interface ItemViewAssertion<A> {
        void check(A item, View view, NoMatchingViewException e);
    }
}
