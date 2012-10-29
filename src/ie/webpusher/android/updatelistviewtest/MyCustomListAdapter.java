package ie.webpusher.android.updatelistviewtest;


import java.util.List;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyCustomListAdapter extends ArrayAdapter<String> {

	private Context context;
	private List<String> flowers;
	
	private final String logKey = "adapter";
	
	public MyCustomListAdapter(Context context,List<String> flowers) {
		super(context, R.layout.mycustomlist, flowers);
		this.context = context;
		this.flowers = flowers;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.d(logKey,String.format("Binding a row item #%1$d", position));
		
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 
		LinearLayout rowLayout = (LinearLayout)inflater.inflate(R.layout.mycustomlist, parent, false);
		TextView rowView = (TextView)rowLayout.findViewById(R.id.theRowItem);
		
		String flowerName = this.flowers.get(position);
		Log.d(logKey,String.format("The flower name is '%1$s'", flowerName));
		
		rowView.setText(flowerName);
		
		// crappy method to load drawables dynamically
		int flowerId = context.getResources().getIdentifier(flowerName, "drawable", this.context.getPackageName());
		BitmapDrawable drawable = (BitmapDrawable)context.getResources().getDrawable(flowerId);
		rowView.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
		
		return rowLayout;
	}
	

}
