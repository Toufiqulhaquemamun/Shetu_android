package com.example.amir.shetu.interfaces.other;

import android.view.View;

public interface ProductClickListener {
    void productClick(View productView, int productId);
    <T> void itemClick(View productView, T item);
}
