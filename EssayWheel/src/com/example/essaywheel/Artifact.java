package com.example.essaywheel;

import android.os.Parcel;
import android.os.Parcelable;

public class Artifact implements Parcelable {
	private String artifact_name;
	private String artifact_creator;
	private Category artifact_category;
	private int artifact_year;
	
	public enum Category {
		Art,
		Literature,
		Science,
		History
	}
	
	public Artifact() {
		artifact_name = "";
		artifact_creator = "";
		artifact_category = Category.Art;
		artifact_year = 2000;
	}
	
	public String getArtifactName() {
		return artifact_name;
	}
	
	public void setArtifactName(String name) {
		artifact_name = name;
	}
	
	public String getCreator() {
		return artifact_creator;
	}
	
	public void setCreatorName(String name) {
		artifact_creator = name;
	}
	
	public Category getCategory() {
		return artifact_category;
	}
	
	public void setCategory(Category category) {
		artifact_category = category;
	}
	
	public int getYear() {
		return artifact_year;
	}
	
	public void setYear(int year) {
		artifact_year = year;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(artifact_name);
		out.writeString(artifact_category.name());
		out.writeString(artifact_creator);
		out.writeInt(artifact_year);
	}

    public static final Parcelable.Creator<Artifact> CREATOR = new Parcelable.Creator<Artifact>() {
        public Artifact createFromParcel(Parcel in) {
            return new Artifact(in);
        }

        public Artifact[] newArray(int size) {
            return new Artifact[size];
        }
    };
    
    private Artifact(Parcel in) {
        artifact_name = in.readString();
        artifact_category = Category.valueOf(in.readString());
        artifact_creator = in.readString();
        artifact_year = in.readInt();
    }
}
