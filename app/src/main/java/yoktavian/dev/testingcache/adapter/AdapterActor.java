package yoktavian.dev.testingcache.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;
import yoktavian.dev.testingcache.R;
import yoktavian.dev.testingcache.models.ActorModels;

/**
 * 
 * Created by yoktavian on 7/18/17.
 *
 */

public class AdapterActor extends RecyclerView.Adapter<AdapterActor.MyViewHolder> {

	private Context mContext;
	private List<ActorModels> data;
	private final OnItemClickListener listener;

	public class MyViewHolder extends RecyclerView.ViewHolder  {
		public TextView txt_name, txt_desc, txt_country;
		public ImageView img_photo;
		public MyViewHolder(View view) {
			super(view);
			txt_name = (TextView) view.findViewById(R.id.name);
			txt_desc = (TextView) view.findViewById(R.id.desc);
			txt_country = (TextView) view.findViewById(R.id.country);
			img_photo = (ImageView) view.findViewById(R.id.photo);
		}

		public void click(final ActorModels models, final OnItemClickListener listener) {
			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					listener.onClick(models);
				}
			});
		}

	}

	public interface OnItemClickListener {
		void onClick(ActorModels item);
	}

	public AdapterActor(Context mContext, List<ActorModels> models, OnItemClickListener listener) {
		this.data=models;
		this.mContext=mContext;
		this.listener = listener;
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.list_view_actors, parent, false);

		return new MyViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(final MyViewHolder holder, final int position) {
		final ActorModels list = data.get(position);
		holder.click(data.get(position), listener);
		Glide.with(mContext)
				.load(list.getImg_url())
				.into(holder.img_photo);
		holder.txt_name.setText(list.getName());
		holder.txt_desc.setText(list.getDescription());
		holder.txt_country.setText(list.getCountry());
	}




	/**
	 * Showing popup menu when tapping on 3 dots
	 */

	/**
	 * Click listener for popup menu items
	 */

	@Override
	public int getItemCount() {
		return data.size();
	}
}