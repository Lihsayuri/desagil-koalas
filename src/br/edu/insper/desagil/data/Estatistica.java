package br.edu.insper.desagil.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estatistica extends DataFrame {
	private Map<String, List<Double>> columns;
	
	public Estatistica() {
		this.columns = new HashMap<>();
	}
	
	@Override
	public void addColumn(String label, List<Double> values) {
		this.columns.put(label, new ArrayList<>(values));
	}

	public double avg(String label) {
		if (!this.columns.containsKey(label)) {
			throw new DataFrameException("Column " + label + " is invalid!");
		}
		List<Double> values = this.columns.get(label);
		if (values.size() == 0) {
			throw new DataFrameException("Column " + label + " is empty!");
		}

		double s = 0;
		for (double value: values) {
			s += value;
		}
		return s / values.size();
	}

	public double var(String label) {
		if (!this.columns.containsKey(label)) {
			throw new DataFrameException("Column " + label + " is invalid!");
		}
		List<Double> values = this.columns.get(label);
		if (values.size() == 0) {
			throw new DataFrameException("Column " + label + " is empty!");
		}

		double s;

		s = 0;
		for (double value: values) {
			s += value;
		}
		double m = s / values.size();

		s = 0;
		for (double value: values) {
			s += Math.pow(value - m, 2);
		}
		return s / values.size();
	}

	public double std(String label) {
		if (!this.columns.containsKey(label)) {
			throw new DataFrameException("Column " + label + " is invalid!");
		}
		List<Double> values = this.columns.get(label);
		if (values.size() == 0) {
			throw new DataFrameException("Column " + label + " is empty!");
		}

		double s, m;

		s = 0;
		for (double value: values) {
			s += value;
		}
		m = s / values.size();

		s = 0;
		for (double value: values) {
			s += Math.pow(value - m, 2);
		}
		m = s / values.size();

		return Math.sqrt(m);
	}

}
