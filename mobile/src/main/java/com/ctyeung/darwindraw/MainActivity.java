package com.ctyeung.darwindraw;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.TextView;

//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import com.ctyeung.darwindraw.viewModels.ActivityMainViewModel;
import com.ctyeung.darwindraw.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PaperEvent{

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private ActivityMainBinding mBinding;
    private MyPaperView paper;
    /*
     * shape drawn, call skeletonization code
     */
    public void onActionUp()
    {
        List<MyPoint> points = paper.getPoints();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setListener(this);

        paper = this.findViewById(R.id.paper);
        paper.setListener(this);
    }

    public void onClickButtonDelete()
    {

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

    public native void distanceMapFromJNI(Bitmap bmpIn,
                                             float[] pointsX,
                                             float[] pointsY,
                                             int count);
}
