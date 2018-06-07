package com.example.expertos.proyectoandroidexpertos2018;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class GalleryFragment extends Fragment implements View.OnClickListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SliderLayout sliderLayout;
    HashMap<String, String> HashMapForURL;
    ImageView imgVBack3;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_gallery, null);

        imgVBack3 = v.findViewById(R.id.imgVBack3);

        imgVBack3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONArray jsonBack;
                try {
                    jsonBack = new JSONArray(getActivity().getIntent().getStringExtra("dataFull"));

                    Intent intentDetail = new Intent(getContext(), SearchResultActivity.class);

                    intentDetail.putExtra("dataBack", jsonBack.toString());

                    startActivity(intentDetail);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        //image slider
        sliderLayout = v.findViewById(R.id.slider);

        //Call this method if you want to add images from URL .
        try {
            AddImagesUrlOnline(getActivity().getIntent().getStringExtra("dataDetail"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Call this method to add images from local drawable folder .
        //AddImageUrlFormLocalRes();

        //Call this method to stop automatic sliding.
        //sliderLayout.stopAutoCycle();

        for (String name : HashMapForURL.keySet()) {

            TextSliderView textSliderView = new TextSliderView(getContext());

            textSliderView
                    .description(name)
                    .image(HashMapForURL.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            textSliderView.bundle(new Bundle());

            textSliderView.getBundle()
                    .putString("extra", name);

            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);

        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        sliderLayout.setCustomAnimation(new DescriptionAnimation());

        sliderLayout.setDuration(3000);

        sliderLayout.addOnPageChangeListener(this);

        //youtube video
        JSONObject jsonDetailCompany;

        try {
            jsonDetailCompany = new JSONObject(getActivity().getIntent().getStringExtra("dataDetail"));


            String videoUrl= jsonDetailCompany.get("video").toString();
            String video= videoUrl.replace("\\", "");

            String frameVideo1 = "<html><body>Video From YouTube<br><iframe width=\"330\" height=\"250\" src=\"";
            String frameVideo2 = "\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
            String finalFrameVideo = frameVideo1+video+frameVideo2;


            WebView displayYoutubeVideo = v.findViewById(R.id.mWebView);
            displayYoutubeVideo.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });
            WebSettings webSettings = displayYoutubeVideo.getSettings();
            webSettings.setJavaScriptEnabled(true);
            displayYoutubeVideo.loadData(finalFrameVideo, "text/html", "utf-8");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return v;
    }

    @Override
    public void onStop() {

        sliderLayout.stopAutoCycle();

        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        Toast.makeText(getContext(), slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void AddImagesUrlOnline(String dataResult) throws JSONException {

        JSONObject jsonDetailCompany;
        JSONArray jsonDetailImages;
        JSONObject jsonImage;

        jsonDetailCompany = new JSONObject(dataResult);
        jsonDetailImages = jsonDetailCompany.getJSONArray("img");
        HashMapForURL = new HashMap<String, String>();

        for (int currentImage = 0; currentImage < jsonDetailImages.length(); currentImage++) {

            jsonImage = jsonDetailImages.getJSONObject(currentImage);

            String imageUrl = jsonImage.get("imgRoute").toString();
            String image = imageUrl.replace("\\", "");

            HashMapForURL.put(jsonDetailCompany.get("nombre").toString(), image);
        }
    }

    @Override
    public void onClick(View v) {

    }
}