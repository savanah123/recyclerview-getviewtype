package safe.highwin.zgs.recyclerviewtype;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Integer> mList;
    private LayoutInflater mInflater;

    public RecyclerViewAdapter(Context context, List<Integer> list) {
        mInflater = LayoutInflater.from(context);
        this.mList = list;
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                View itemIV = mInflater.inflate(R.layout.item_imageview, parent, false);
                return new ImageViewHolder(itemIV);
            case 1:
                View itemTV = mInflater.inflate(R.layout.item_textview, parent, false);
                return new TextViewHolder(itemTV);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
                ImageViewHolder ivHolder = (ImageViewHolder) holder;
                ivHolder.iv.setImageResource(R.mipmap.ic_launcher);

                break;
            case 1:
                TextViewHolder tvHolder = (TextViewHolder) holder;
                tvHolder.tv.setText("hello type0");
                break;
        }
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class TextViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        public TextViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView iv;

        public ImageViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }


}
