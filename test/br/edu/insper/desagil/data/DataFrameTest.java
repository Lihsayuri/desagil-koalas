package br.edu.insper.desagil.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataFrameTest {
	private static double DELTA = 0.000001;

	private Estatistica e;
	private Sequencial s;

	@BeforeEach
	public void setUp() {
		e = new Estatistica();
		s = new Sequencial();
	}

	@Test
	public void invalidMin() {
		DataFrameException e = assertThrows(DataFrameException.class, () -> {
			s.min("invalidMin");
		});
		assertEquals("Column invalidMin is invalid!", e.getMessage());
	}

	@Test
	public void invalidMax() {
		DataFrameException e = assertThrows(DataFrameException.class, () -> {
			s.max("invalidMax");
		});
		assertEquals("Column invalidMax is invalid!", e.getMessage());
	}

	@Test
	public void invalidSum() {
		DataFrameException e = assertThrows(DataFrameException.class, () -> {
			s.sum("invalidSum");
		});
		assertEquals("Column invalidSum is invalid!", e.getMessage());
	}

	@Test
	public void invalidAvg() {
		DataFrameException e = assertThrows(DataFrameException.class, () -> {
			e.avg("invalidAvg");
		});
		assertEquals("Column invalidAvg is invalid!", e.getMessage());
	}

	@Test
	public void invalidVar() {
		DataFrameException e = assertThrows(DataFrameException.class, () -> {
			e.var("invalidVar");
		});
		assertEquals("Column invalidVar is invalid!", e.getMessage());
	}

	@Test
	public void invalidStd() {
		DataFrameException e = assertThrows(DataFrameException.class, () -> {
			e.std("invalidStd");
		});
		assertEquals("Column invalidStd is invalid!", e.getMessage());
	}

	@Test
	public void emptyMin() {
		s.addColumn("emptyMin", new ArrayList<>());
		DataFrameException e = assertThrows(DataFrameException.class, () -> {
			s.min("emptyMin");
		});
		assertEquals("Column emptyMin is empty!", e.getMessage());
	}

	@Test
	public void emptyMax() {
		s.addColumn("emptyMax", new ArrayList<>());
		DataFrameException e = assertThrows(DataFrameException.class, () -> {
			s.max("emptyMax");
		});
		assertEquals("Column emptyMax is empty!", e.getMessage());
	}

	@Test
	public void emptySum() {
		s.addColumn("emptySum", new ArrayList<>());
		DataFrameException e = assertThrows(DataFrameException.class, () -> {
			s.sum("emptySum");
		});
		assertEquals("Column emptySum is empty!", e.getMessage());
	}

	@Test
	public void emptyAvg() {
		e.addColumn("emptyAvg", new ArrayList<>());
		DataFrameException e = assertThrows(DataFrameException.class, () -> {
			e.avg("emptyAvg");
		});
		assertEquals("Column emptyAvg is empty!", e.getMessage());
	}

	@Test
	public void emptyVar() {
		e.addColumn("emptyVar", new ArrayList<>());
		DataFrameException e = assertThrows(DataFrameException.class, () -> {
			e.var("emptyVar");
		});
		assertEquals("Column emptyVar is empty!", e.getMessage());
	}

	@Test
	public void emptyStd() {
		e.addColumn("emptyStd", new ArrayList<>());
		DataFrameException e = assertThrows(DataFrameException.class, () -> {
			e.std("emptyStd");
		});
		assertEquals("Column emptyStd is empty!", e.getMessage());
	}

	@Test
	public void oneValue() {
		List<Double> values = new ArrayList<>();
		values.add(4.2);
		//d.addColumn("oneValue", values);

		assertEquals(4.2, s.min("oneValue"), DELTA);
		assertEquals(4.2, s.max("oneValue"), DELTA);
		assertEquals(4.2, s.sum("oneValue"), DELTA);
		assertEquals(4.2, e.avg("oneValue"), DELTA);
		assertEquals(0.0, e.var("oneValue"), DELTA);
		assertEquals(0.0, e.std("oneValue"), DELTA);
	}

	@Test
	public void twoEqualValues() {
		List<Double> values = new ArrayList<>();
		values.add(4.2);
		values.add(4.2);

		assertEquals(4.2, s.min("twoEqualValues"), DELTA);
		assertEquals(4.2, s.max("twoEqualValues"), DELTA);
		assertEquals(8.4, s.sum("twoEqualValues"), DELTA);
		assertEquals(4.2, e.avg("twoEqualValues"), DELTA);
		assertEquals(0.0, e.var("twoEqualValues"), DELTA);
		assertEquals(0.0, e.std("twoEqualValues"), DELTA);
	}

	@Test
	public void twoAscendingValues() {
		List<Double> values = new ArrayList<>();
		values.add(3.5);
		values.add(4.2);

		assertEquals(3.5   , s.min("twoAscendingValues"), DELTA);
		assertEquals(4.2   , s.max("twoAscendingValues"), DELTA);
		assertEquals(7.7   , s.sum("twoAscendingValues"), DELTA);
		assertEquals(3.85  , e.avg("twoAscendingValues"), DELTA);
		assertEquals(0.1225, e.var("twoAscendingValues"), DELTA);
		assertEquals(0.35  , e.std("twoAscendingValues"), DELTA);
	}

	@Test
	public void twoDescendingValues() {
		List<Double> values = new ArrayList<>();
		values.add(4.2);
		values.add(3.5);

		assertEquals(3.5   , s.min("twoDescendingValues"), DELTA);
		assertEquals(4.2   , s.max("twoDescendingValues"), DELTA);
		assertEquals(7.7   , s.sum("twoDescendingValues"), DELTA);
		assertEquals(3.85  , e.avg("twoDescendingValues"), DELTA);
		assertEquals(0.1225, e.var("twoDescendingValues"), DELTA);
		assertEquals(0.35  , e.std("twoDescendingValues"), DELTA);
	}

	@Test
	public void threeEqualValues() {
		List<Double> values = new ArrayList<>();
		values.add(4.2);
		values.add(4.2);
		values.add(4.2);

		assertEquals( 4.2, s.min("threeEqualValues"), DELTA);
		assertEquals( 4.2, s.max("threeEqualValues"), DELTA);
		assertEquals(12.6, s.sum("threeEqualValues"), DELTA);
		assertEquals( 4.2, e.avg("threeEqualValues"), DELTA);
		assertEquals( 0.0, e.var("threeEqualValues"), DELTA);
		assertEquals( 0.0, e.std("threeEqualValues"), DELTA);
	}

	@Test
	public void threeAscendingValues() {
		List<Double> values = new ArrayList<>();
		values.add(3.5);
		values.add(4.2);
		values.add(6.7);
	

		assertEquals( 3.5     , s.min("threeAscendingValues"), DELTA);
		assertEquals( 6.7     , s.max("threeAscendingValues"), DELTA);
		assertEquals(14.4     , s.sum("threeAscendingValues"), DELTA);
		assertEquals( 4.8     , e.avg("threeAscendingValues"), DELTA);
		assertEquals( 1.886666, e.var("threeAscendingValues"), DELTA);
		assertEquals( 1.373559, e.std("threeAscendingValues"), DELTA);
	}

	@Test
	public void threeDescendingValues() {
		List<Double> values = new ArrayList<>();
		values.add(6.7);
		values.add(4.2);
		values.add(3.5);


		assertEquals( 3.5     , s.min("threeDescendingValues"), DELTA);
		assertEquals( 6.7     , s.max("threeDescendingValues"), DELTA);
		assertEquals(14.4     , s.sum("threeDescendingValues"), DELTA);
		assertEquals( 4.8     , e.avg("threeDescendingValues"), DELTA);
		assertEquals( 1.886666, e.var("threeDescendingValues"), DELTA);
		assertEquals( 1.373559, e.std("threeDescendingValues"), DELTA);
	}
}
