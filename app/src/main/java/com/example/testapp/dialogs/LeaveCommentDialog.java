package com.example.testapp.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.testapp.R;
import com.example.testapp.common.Common;
import com.example.testapp.common.Utils;
import com.example.testapp.mvp.models.CommentPost;
import com.example.testapp.mvp.models.Product;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeaveCommentDialog extends Dialog {

    public interface OnButtonSendCommentClickListener {
        void OnButtonSendCommentClick(CommentPost commentToSend);
    }

    private OnButtonSendCommentClickListener mOnButtonSendCommentClickListener;
    private Product mProduct;

    @BindView(R.id.rateComment)
    RatingBar mRatingBarDialog;

    @BindView(R.id.editTextComment)
    TextInputEditText mTextInputEditTextComment;

    @BindView(R.id.textInputLayout)
    TextInputLayout mTextInputLayout;

    @BindView(R.id.btnSendComment_Dialog)
    Button mButtonSendComment;


    public LeaveCommentDialog(@NonNull Context context, int themeResId, Product product) {
        super(context, themeResId);
        mProduct = product;
        setupDialog();
    }

    private void setupDialog() {
        setTitle("Comment for " + mProduct.getTitle());
        setContentView(R.layout.review_dialog);
        setCancelable(true);
        ButterKnife.bind(this);
        mButtonSendComment.setOnClickListener(view -> {
            dismiss();
            String textComment = mTextInputEditTextComment.getText().toString();
            int rateComment = (int)mRatingBarDialog.getRating();
            mOnButtonSendCommentClickListener.OnButtonSendCommentClick(new CommentPost(rateComment, textComment));
        });
        String token = Utils.getUserData(Common.KEY_TOKEN);
        if(token == null){
            mTextInputEditTextComment.setText("No token");
        }
        else{
            mTextInputEditTextComment.setText(token.substring(5,8));
        }
    }

    public void setmOnButtonSendCommentClickListener(OnButtonSendCommentClickListener mOnButtonSendCommentClickListener) {
        this.mOnButtonSendCommentClickListener = mOnButtonSendCommentClickListener;
    }

    /*private static final class ActionListener implements TextView.OnEditorActionListener {
        private final WeakReference<LeaveCommentDialog> leaveCommentDialogWeakReference;

        public static ActionListener newInstance(LeaveCommentDialog leaveCommentDialog) {
            WeakReference<LeaveCommentDialog> leaveCommentDialogWeakReference = new WeakReference<>(leaveCommentDialog);
            return new ActionListener(leaveCommentDialogWeakReference);
        }

        private ActionListener(WeakReference<LeaveCommentDialog> leaveCommentDialogWeakReference) {
            this.leaveCommentDialogWeakReference = leaveCommentDialogWeakReference;
        }

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            LeaveCommentDialog mainActivity = leaveCommentDialogWeakReference.get();
            if (mainActivity != null) {
                if (actionId == EditorInfo.IME_ACTION_GO && mainActivity.shouldShowError()) {
                    mainActivity.showMessage();
                } else {
                    mainActivity.hideError();
                }
            }
            return true;
        }
    }*/

}
