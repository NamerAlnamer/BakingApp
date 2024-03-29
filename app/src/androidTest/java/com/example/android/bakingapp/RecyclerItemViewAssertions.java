package com.example.android.bakingapp;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.util.HumanReadables;

public class RecyclerItemViewAssertions<A> implements ViewAssertion {

    private int position;
    private A item;
    private RecyclerViewInteractions.ItemViewAssertion<A> itemViewAssertion;

    public RecyclerItemViewAssertions(int position, A item, RecyclerViewInteractions.ItemViewAssertion<Object> itemViewAssertion) {
        this.position = position;
        this.item = item;
        this.itemViewAssertion = (RecyclerViewInteractions.ItemViewAssertion<A>) itemViewAssertion;
    }

    @Override
    public final void check(View view, NoMatchingViewException e) {
        RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView.ViewHolder viewHolderForPosition = recyclerView.findViewHolderForLayoutPosition(position);
        if (viewHolderForPosition == null) {
            throw (new PerformException.Builder())
                    .withActionDescription(toString())
                    .withViewDescription(HumanReadables.describe(view))
                    .withCause(new IllegalStateException("No view holder at position: " + position))
                    .build();
        } else {
            View viewAtPosition = viewHolderForPosition.itemView;
            itemViewAssertion.check(item, viewAtPosition, e);
        }
    }

}
