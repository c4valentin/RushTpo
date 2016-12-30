package beastandroiddev.rushtpo.views.AboutUsViews;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import beastandroiddev.rushtpo.R2;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutUsSimpleHeader extends RecyclerView.ViewHolder {
    @BindView(R2.id.simple_header_textView)
    TextView mTextHeader;

    public AboutUsSimpleHeader(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void populate(String cardsTitle){
        mTextHeader.setText(cardsTitle);
    }
}
