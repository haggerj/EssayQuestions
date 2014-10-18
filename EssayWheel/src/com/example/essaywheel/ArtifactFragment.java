package com.example.essaywheel;

import com.example.essaywheel.Artifact.Category;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ArtifactFragment extends Fragment {
	private Artifact artifact = new Artifact();;
	private EditText artifact_name_entry;
	private EditText artifact_creator_entry;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflator, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflator.inflate(R.layout.fragment_artifact, parent, false);

		artifact_name_entry = (EditText)v.findViewById(R.id.artifact_name_entry);
		artifact_name_entry.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence c, int start, int before, int count) {
				artifact.setArtifactName(c.toString());
			}

			@Override
			public void afterTextChanged(Editable s) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});
		
		artifact_creator_entry = (EditText)v.findViewById(R.id.artifact_creator_entry);
		artifact_creator_entry.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence c, int start, int before, int count) {
				artifact.setCreatorName(c.toString());
			}

			@Override
			public void afterTextChanged(Editable s) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});
		
		Spinner artifact_type_spinner = (Spinner)v.findViewById(R.id.artifact_type_entry);
		ArrayAdapter<CharSequence> artifact_types = ArrayAdapter.createFromResource(this.getActivity().getApplicationContext(), R.array.artifact_types, android.R.layout.simple_dropdown_item_1line);
		artifact_types.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		artifact_type_spinner.setAdapter(artifact_types);
		
		artifact_type_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View v, int position, long id)  {
				artifact.setCategory(Category.values()[position]);
		    }

			@Override
			public void onNothingSelected(AdapterView<?> arg0) { }
		});
		
		Spinner artifact_year_spinner = (Spinner)v.findViewById(R.id.artifact_year_entry);
		Integer[] items = new Integer[5000];
		
		for (int i = 0; i < items.length; i++) {
			items[i] = i - 2600;
		}
		
		ArrayAdapter<Integer> year_adapter = new ArrayAdapter<Integer>(this.getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, items);
		artifact_year_spinner.setAdapter(year_adapter);
		
		Button save_button = (Button)v.findViewById(R.id.save_button);
		save_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				returnResult();
			}
		});
		
		return v;
	}
	
	private void returnResult() {
		Intent returnIntent = new Intent();
		
		if (artifact != null) {
			returnIntent.putExtra("artifact", artifact);
		} else {
			returnIntent.putExtra("artifact", new Artifact());
		}
		getActivity().setResult(Activity.RESULT_OK, returnIntent);
		getActivity().finish();	
	}

}
