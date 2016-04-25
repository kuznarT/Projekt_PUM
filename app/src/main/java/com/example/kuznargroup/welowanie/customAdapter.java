package com.example.kuznargroup.welowanie;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class customAdapter extends PagerAdapter {

    public int[] levels = {R.drawable.ip_sem_jeden, R.drawable.ip_sem_dwa, R.drawable.ip_sem_trzy, R.drawable.ic_sem_czt,
            R.drawable.ip_sem_piat, R.drawable.ip_sem_szes};

    private Context ctx;
    private LayoutInflater layoutInflater;

    public customAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return levels.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swipe_select_stage, container, false);
        ImageView imageView = (ImageView) item_view.findViewById(R.id.image_view);
        imageView.setImageResource(levels[position]);
        container.addView(item_view);
        Button button = (Button) item_view.findViewById(R.id.startGameButton);
        button.setText(ctx.getString(R.string.nameSemestr) + ++position);
        return item_view;


    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}