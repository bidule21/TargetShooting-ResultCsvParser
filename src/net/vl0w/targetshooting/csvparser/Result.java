package net.vl0w.targetshooting.csvparser;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Result {
	private float score;
	private String category;
	@SerializedName("consists_of")
	private List<Result> children;

	public Result(RawLine line) {
		score = line.getResult();
		category = line.getCategory();
	}

	public String getCategory() {
		return category;
	}

	public List<Result> getChildren() {
		if (children == null) {
			children = new ArrayList<Result>();
		}
		return children;
	}

	public int shotCount() {
		CategoryAnalyzer analyzer = new CategoryAnalyzer(category);
		return analyzer.getShotCount();
	}

	@Override
	public String toString() {
		return category + "/" + score;
	}
}
