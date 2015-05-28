package net.vl0w.targetshooting.csvparser;
import java.util.ArrayList;
import java.util.List;

public class ResultBundler {

	private List<Result> results;

	public ResultBundler(List<Result> results) {
		this.results = results;
	}

	public Result bundle() {
		while (results.size() > 1) {
			List<Result> bundle = new ArrayList<Result>();
			for (int i = 0; i < results.size(); i++) {
				Result current = results.get(i);
				Result next = results.get(i + 1);
				if (current.shotCount() == next.shotCount()
						&& !anyLowerBundles(current.shotCount(), results)) {
					bundle.add(current);
				} else if (current.shotCount() < next.shotCount()) {
					bundle.add(current);
					next.getChildren().addAll(bundle);
					results.removeAll(bundle);
					break;
				}
			}
		}
		return results.get(0);
	}

	private boolean anyLowerBundles(int shotCount, List<Result> results) {
		for (Result result : results) {
			if (result.shotCount() < shotCount)
				return true;
		}
		return false;
	}
}
