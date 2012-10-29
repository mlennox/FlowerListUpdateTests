package ie.webpusher.android.updatelistviewtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

// resources for flower images from http://en.wikipedia.org/wiki/File:Flower_poster_2.jpg

public class UpdateListViewExample extends Activity {

	private final String logKey = "MainActivity";
	private boolean chooseEven = true;
	// yes, yes this could be declared dynamically - I'm *only* concerned with the list updating here
	private final List<String> flowers = Arrays.asList("flower1", "flower2", "flower3", "flower4", "flower5");
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(logKey,"onCreate");
        setContentView(R.layout.activity_update_list_view_example);
        
        BindFlowerList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_update_list_view_example, menu);
        return true;
    }
    
    private void BindFlowerList(){
    	Log.d(logKey,String.format("Binding flower list - do we use even? : %1$b", this.chooseEven));
    	
    	ArrayList<String> newFlowerList = new ArrayList<String>();
    	for (int flower = chooseEven ? 0 : 1 ; flower < this.flowers.size() ; flower += 2){
    		newFlowerList.add(this.flowers.get(flower));
    	}
    	Log.d(logKey,String.format("We'll be binding %1$d items",newFlowerList.size()));
    	
    	MyCustomListAdapter adapter = new MyCustomListAdapter(this, newFlowerList);
    	
    	ListView flowerList = (ListView)findViewById(R.id.flowerList);
    	flowerList.setAdapter(adapter);
    	
    	adapter.notifyDataSetChanged();
    	
    	// next time we choose the odd elements
    	this.chooseEven = !this.chooseEven;
    }
    
    // I really don't like this, but we are working on list updating here, not click handlers
    public void handleFlowerSwitch(View v){
    	BindFlowerList();
    }
}
