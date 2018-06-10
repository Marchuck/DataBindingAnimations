package pl.marchuck.databindinganimations

import android.databinding.BindingAdapter
import android.support.design.widget.TextInputLayout
import android.support.transition.TransitionManager
import android.view.View
import android.view.ViewGroup

@BindingAdapter("android:animatedVisibility")
fun setAnimatedVisibility(target: View, isVisible: Boolean) {
    TransitionManager.beginDelayedTransition(target.rootView as ViewGroup)
    target.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("android:error")
fun setError(view: TextInputLayout, sequence: CharSequence?) {
    if (sequence == null || sequence.isEmpty()) {
        view.error = null
    } else {
        view.error = sequence
    }
}




