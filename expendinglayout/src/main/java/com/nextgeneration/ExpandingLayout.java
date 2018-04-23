package com.nextgeneration;

import android.animation.LayoutTransition;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.nextgeneration.expandinglayout.R;

public class ExpandingLayout extends LinearLayout {

	private static final String TAG = ExpandingLayout.class.getSimpleName();
	private View[] mViews;
	private int mMinChildren = 1;

	public ExpandingLayout(Context context) {
		super(context);
	}

	public ExpandingLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	@TargetApi(11)
	public ExpandingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	@TargetApi(21)
	public ExpandingLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init(context, attrs);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		if (mViews == null) {
			mViews = new View[this.getChildCount()];
			for (int i = 0; i < this.getChildCount(); i++) {
				mViews[i] = this.getChildAt(i);
			}
		}
	}

	private void init(Context context, AttributeSet attrs) {
		TypedArray params = context.obtainStyledAttributes(attrs, R.styleable.ExpandingLayout, 0, 0);
		mMinChildren = params.getInt(R.styleable.ExpandingLayout_minChildren, 1);
		params.recycle();
		this.setLayoutTransition();
	}


	@TargetApi(11)
	public void setLayoutTransition() {
		if (Build.VERSION.SDK_INT > 11) {
			super.setLayoutTransition(new LayoutTransition());
		}
	}

	public boolean isExpanded() {
		return this.getChildCount() == mViews.length;
	}

	public void expandOrHide() {
		if (isExpanded()) {
			hide();
		} else {
			expand();
		}
	}

	public void expand() {
		if (isExpanded()) {
			Log.w(TAG, "All views are already shown, call the other method");
			return;
		}
		for (int i = this.getChildCount(); i < mViews.length; i++) {
			this.addView(mViews[i], i);
		}
	}

	public void hide() {
		if (!isExpanded()) {
			Log.w(TAG, "Some views are already hidden, call the other method");
			return;
		}
		//控制收起时显示的行数，，目前设置为3.
		if (this.getChildCount()>3) {
			for (int i = this.getChildCount() - 1; i > 2; i--) {
				this.removeViewAt(i);
			}
		}else{
			for (int i = this.getChildCount() - 1; i > mMinChildren - 1; i--) {
				this.removeViewAt(i);
			}
		}
	}

	@Override
	protected Parcelable onSaveInstanceState() {
		Parcelable superState = super.onSaveInstanceState();
		return new SavedState(superState, mMinChildren, isExpanded());
	}

	@Override
	protected void onRestoreInstanceState(Parcelable state) {
		if (state instanceof SavedState) {
			SavedState mSavedState = (SavedState) state;
			this.mMinChildren = mSavedState.mMinChildren;
			if (!mSavedState.mExpanded) {
				hide();
			}
			super.onRestoreInstanceState(mSavedState.getSuperState());
		} else {
			super.onRestoreInstanceState(state);
		}
	}


	static class SavedState extends View.BaseSavedState {

		public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {

			@Override
			public SavedState createFromParcel(Parcel parcel) {
				return new SavedState(parcel);
			}

			@Override
			public SavedState[] newArray(int i) {
				return new SavedState[i];
			}
		};
		private int mMinChildren;
		private boolean mExpanded;

		public SavedState(Parcelable superState, int mMinChildren, boolean mExpanded) {
			super(superState);
			this.mMinChildren = mMinChildren;
			this.mExpanded = mExpanded;
		}

		public SavedState(Parcel parcel) {
			super(parcel);
			this.mMinChildren = parcel.readInt();
			boolean[] array = new boolean[1];
			parcel.readBooleanArray(array);
			this.mExpanded = array[0];
		}

		@Override
		public void writeToParcel(Parcel out, int flags) {
			super.writeToParcel(out, flags);
			out.writeInt(this.mMinChildren);
			out.writeBooleanArray(new boolean[]{mExpanded});
		}
	}
}
