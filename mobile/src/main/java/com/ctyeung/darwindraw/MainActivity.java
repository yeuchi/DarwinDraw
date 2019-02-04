package com.ctyeung.darwindraw;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import com.ctyeung.darwindraw.utilities.BitmapUtil;
import com.ctyeung.darwindraw.viewModels.ActivityMainViewModel;
import com.ctyeung.darwindraw.databinding.ActivityMainBinding;
import com.ctyeung.darwindraw.viewModels.BoundRect;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PaperEvent{

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private ActivityMainBinding mBinding;
    private MyPaperView paper;
    private float[] pointsX;
    private float[] pointsY;

    /*
     * shape drawn, call skeletonization code
     */
    public void onActionUp()
    {
        List<MyPoint> points = paper.getPoints();
        if(null==points || 0==points.size())
        {
            Log.d("onActionUp", "No points to compute distanceMap");
            return;
        }

        // create bitmap
        BoundRect boundRect = new BoundRect(1000, 0, 1000, 0);
        Bitmap bmp = BitmapUtil.renderView(paper, boundRect);

        pointsX = new float[points.size()];
        pointsY = new float[points.size()];
        for(int i=0; i<points.size(); i++)
        {
            MyPoint p = points.get(i);

            // normalize the points for selected rect bound data
            pointsX[i] = p.x - boundRect.minX;
            pointsY[i] = p.y - boundRect.minY;
        }

        // draw skeleton
        distanceMapFromJNI(bmp, pointsX, pointsY, points.size());

        // insert image into canvas
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
