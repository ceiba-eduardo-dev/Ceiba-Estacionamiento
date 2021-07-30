package com.ceiba.estacionamiento.shared.controller.extensions

import android.view.View


fun View.isHide(isGone: Boolean) {
    visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

