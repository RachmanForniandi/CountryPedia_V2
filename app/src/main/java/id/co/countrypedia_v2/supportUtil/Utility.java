package id.co.countrypedia_v2.supportUtil;

import android.content.Context;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import id.co.countrypedia_v2.R;

public class Utility {
    public static void loadImageFlag(ImageView img, String url, CircularProgressDrawable progressDrawable){
        RequestOptions options = new RequestOptions()
                .placeholder(progressDrawable)
                .error(R.mipmap.ic_launcher_round);
        Glide.with(img.getContext())
                .setDefaultRequestOptions(options)
                .load(url)
                .into(img);
    }

    public static CircularProgressDrawable loadProgressDrawable(Context context){
        CircularProgressDrawable progressDrawable = new CircularProgressDrawable(context);
        progressDrawable.setStrokeWidth(10f);
        progressDrawable.setCenterRadius(50f);
        progressDrawable.start();
        return progressDrawable;
    }
}
