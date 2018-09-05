package com.example.hernandramosb.memdetrabajotesis.spandesimbolos;

import android.widget.ImageButton;

public class SSOption {
    private Integer imageId = null;
    private boolean showed;
    private Integer position;
    private ImageButton button;



    public SSOption(int imageId, ImageButton button) {
        this.imageId = imageId;
        this.showed = false;
        this.button = button;

    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public boolean wasShowed() {
        return showed;
    }

    public void setShowed(boolean showed) {
        this.showed = showed;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ImageButton getButton() {
        return button;
    }
}
