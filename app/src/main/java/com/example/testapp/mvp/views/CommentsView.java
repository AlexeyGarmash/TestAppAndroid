package com.example.testapp.mvp.views;

import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.testapp.mvp.models.Comment;

import java.util.ArrayList;

public interface CommentsView extends BaseLceView {

    @StateStrategyType(AddToEndStrategy.class)
    void showComments(ArrayList<Comment> comments);

    @StateStrategyType(SingleStateStrategy.class)
    void clearComments();
}
