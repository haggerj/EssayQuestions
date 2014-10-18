package com.example.essaywheel;

import java.util.HashMap;
import java.util.Map;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EssayQuestionActivity extends ActionBarActivity {

	Map<StringTuple, Integer> stringResources;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_essay_question);
		
		stringResources = new HashMap<StringTuple, Integer>();
		stringResources.put(new StringTuple("Art", "context"), R.array.art_context);
		stringResources.put(new StringTuple("Art", "heuristic"), R.array.art_heuristic);
		stringResources.put(new StringTuple("Art", "structure"), R.array.art_structure);
		stringResources.put(new StringTuple("Art", "critic"), R.array.art_critics);
		stringResources.put(new StringTuple("Art", "creator"), R.array.art_creator);
		
		stringResources.put(new StringTuple("Literature", "context"), R.array.literature_context);
		stringResources.put(new StringTuple("Literature", "heuristic"), R.array.literature_heuristic);
		stringResources.put(new StringTuple("Literature", "structure"), R.array.literature_structure);
		stringResources.put(new StringTuple("Literature", "critic"), R.array.literature_critics);
		stringResources.put(new StringTuple("Literature", "creator"), R.array.literature_creator);

		stringResources.put(new StringTuple("Science", "context"), R.array.science_context);
		stringResources.put(new StringTuple("Science", "heuristic"), R.array.science_heuristic);
		stringResources.put(new StringTuple("Science", "structure"), R.array.science_structure);
		stringResources.put(new StringTuple("Science", "critic"), R.array.science_critics);
		stringResources.put(new StringTuple("Science", "creator"), R.array.science_creator);
		
		stringResources.put(new StringTuple("History", "context"), R.array.history_context);
		stringResources.put(new StringTuple("History", "heuristic"), R.array.history_heuristic);
		stringResources.put(new StringTuple("History", "structure"), R.array.history_structure);
		stringResources.put(new StringTuple("History", "critic"), R.array.history_critics);
		stringResources.put(new StringTuple("History", "creator"), R.array.history_creator);
		
		Artifact artifact = getIntent().getParcelableExtra("artifact");
		String buttonType = getIntent().getStringExtra("buttonClick");
		
		ListView questionList = (ListView)findViewById(R.id.question_list);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_list_item_1, getStringArray(artifact, buttonType, stringResources));
		
		questionList.setAdapter(adapter);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private String[] getStringArray(Artifact artifact, String buttonName, Map<StringTuple, Integer> resources) {
		String category = artifact.getCategory().name();
		Integer id = resources.get(new StringTuple(artifact.getCategory().name(), buttonName));
		String[] result = getResources().getStringArray(id);
		
		String toReplace;
		
		if (buttonName == "context" || buttonName == "structure") {
			toReplace = artifact.getCreator();
		} else {
			toReplace = artifact.getArtifactName();
		}
		
		for(int i = 0; i < result.length; i++) {
			result[i] = String.format(result[i], toReplace);
		}
		
		return result;
	}
	
	private class StringTuple{
		public String[] values;
		
		public StringTuple(String val1, String val2) {
			values = new String[2];
			values[0] = val1;
			values[1] = val2;
		}
		
		@Override
		public int hashCode() {
			return values[0].hashCode() * 17 + values[1].hashCode();
		}
		
		@Override
		public boolean equals(Object other) {
			if (!(other instanceof StringTuple) ) {
				return false;
			}
			
			StringTuple otherTuple = (StringTuple)other;
			
			if (otherTuple.values[0] == null) {
				if (this.values[0] != null) {
					return false;
				}
			}
			
			if (otherTuple.values[1] == null) {
				if (this.values[1] == null && 
						(otherTuple.values[0] == null || otherTuple.values[0].equals(this.values[0])));
			}
			
			return otherTuple.values[0].equals(this.values[0]) && 
					otherTuple.values[1].equals(this.values[1]);
		}
	}
}
