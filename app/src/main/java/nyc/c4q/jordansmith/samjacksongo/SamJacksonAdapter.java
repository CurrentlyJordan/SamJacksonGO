package nyc.c4q.jordansmith.samjacksongo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import nyc.c4q.jordansmith.samjacksongo.Model.SamJackson;

/**
 * Created by jordansmith on 12/14/16.
 */

public class SamJacksonAdapter extends RecyclerView.Adapter<SamJacksonAdapter.SamJacksonViewHolder> {

    List<SamJackson> samJacksonList;
    Listener listener;

    public SamJacksonAdapter(List<SamJackson> samJacksonList, Listener listener){
        this.listener = listener;
        this.samJacksonList = samJacksonList;
    }

    @Override
    public SamJacksonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View childview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);

        return new SamJacksonViewHolder(childview);
    }

    @Override
    public void onBindViewHolder(final SamJacksonViewHolder holder, int position) {
        holder.name_text_view.setText(samJacksonList.get(position).getName());
        Glide.with(holder.itemView.getContext()).load(samJacksonList.get(position).getImageUrl()).centerCrop().into(holder.sam_jackson_image_view);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onSamJacksonLongClicked(samJacksonList.get(holder.getAdapterPosition()));
                return true;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onSamJacksonShortClicked(samJacksonList.get(holder.getAdapterPosition()));
            }
        });






    }

    @Override
    public int getItemCount() {
        return samJacksonList.size();
        }

    public void setData(List<SamJackson> samJacksonList){
        this.samJacksonList = samJacksonList;
        notifyDataSetChanged();
    }

    public class SamJacksonViewHolder extends RecyclerView.ViewHolder{
        TextView name_text_view;
        ImageView sam_jackson_image_view;

        public SamJacksonViewHolder(View itemView) {
            super(itemView);

            name_text_view = (TextView)itemView.findViewById(R.id.name_text_view);
            sam_jackson_image_view = (ImageView)itemView.findViewById(R.id.sam_jackson_image_view);
        }
    }

    interface Listener{
        void onSamJacksonLongClicked(SamJackson sam);
        void onSamJacksonShortClicked(SamJackson sam);
    }
}
