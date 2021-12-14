package data_mining_Presentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
 * LaGrange Polynomials
 * @author MCatt
 */

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		String file_name = null;
		if (args.length > 0) {
			file_name = args[0];
		} else {
			file_name = new String("testinput.txt");
		}
		Scanner scan = null;
		scan = new Scanner(new File("testinput.txt"));
		scan.nextLine();
		BigDecimal[][] data = new BigDecimal[scan.nextInt()][scan.nextInt()];
		BigDecimal[] f_values = new BigDecimal[data[0].length];

		scan.nextLine();
		scan.nextLine();
		String s = scan.nextLine();

		int ind_selection = 0;
		int dep_selection = 0;
		List<Integer> independent_selections = new ArrayList<Integer>();
		List<Integer> dependent_selections = new ArrayList<Integer>();
		// System.out.println(s);
		if (s.equals("-1")) {
			dep_selection = -1;
		} else {
			for (String i : s.split("\t")) {

				dependent_selections.add(Integer.parseInt(i));
			}

		}
		s = scan.nextLine();
		s = scan.nextLine();
		if (s.equals("-1")) {
			ind_selection = -1;
		} else {
			for (String i : s.split("\t")) {

				independent_selections.add(Integer.parseInt(i));
			}

		}
		s = scan.nextLine();
		s = scan.nextLine();
		int error_check = Integer.parseInt(s);

		s = scan.nextLine();

		for (int i = 0, j = 0; j < data[0].length; j++) {
			s = scan.nextLine();
			for (String term : s.split("\t")) {
				if (i == data.length) {
					if (term.equals("?")) {
						f_values[j] = null;
					} else {
						f_values[j] = new BigDecimal(term);
					}
				} else {
					data[i][j] = new BigDecimal(term);
				}

				i++;
			}
			i = 0;
		}

		if (ind_selection == -1) {
			for (int i = 0; i < data.length; i++) {
				independent_selections.add(i);
			}

		}

		if (dep_selection == -1) {
			for (int i = 0; i < f_values.length; i++) {
				if (f_values[i] == null) {
					dependent_selections.add(i);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				sb.append(data[i][j] + " ");
			}
			sb.append("\n");
		}

		Lagrange l_data_model = new Lagrange(data, f_values,
				(independent_selections.toArray(new Integer[independent_selections.size()])),
				(dependent_selections.toArray(new Integer[dependent_selections.size()])), error_check);

	}
}

class Lagrange {
	BigDecimal data[][];
	BigDecimal f_values[];
	List<Integer> missing_indices;
	List<Integer> not_missing_indices;
	List<List<Polynomial>> numerators;
	List<List<BigDecimal>> denominators;

	public Lagrange(BigDecimal[][] data, BigDecimal[] f_values, Integer[] ind_selection, Integer[] dep_selection,
			int error_check) {
		missing_indices = new ArrayList<Integer>();
		not_missing_indices = new ArrayList<Integer>();

		for (int i = 0, index = 0; i < f_values.length; i++) {
			if (f_values[i] == null) {
				missing_indices.add(i);
			}else {
				not_missing_indices.add(i);
			}
		}


		numerators = new ArrayList<List<Polynomial>>();
		denominators = new ArrayList<List<BigDecimal>>();
		StringBuilder sb = new StringBuilder();
		sb.append("\n");


		for (int i = 0; i < data.length; i++) {
			List<Polynomial> num_terms = new ArrayList<Polynomial>();
			List<BigDecimal> den_terms = new ArrayList<BigDecimal>();
			for (int j = 0; j < data[i].length; j++) {

				if (!missing_indices.contains(j)) {

					BigDecimal denominator = BigDecimal.ONE;
					Polynomial numerator = null;
					for (int k = ((j + 1) % (data[i].length)); k != j; k = (k + 1) % (data[i].length)) {

						if (missing_indices.contains(k)) {
							continue;
						}
						denominator = denominator.multiply(data[i][j].subtract(data[i][k]));

						if (numerator == null) {
							numerator = new Polynomial(1,
									new BigDecimal[] { BigDecimal.ONE, (data[i][k].multiply(new BigDecimal("-1"))) });
							sb.append(numerator.toString(i));
						} else {

							Polynomial temp_num = new Polynomial(1,
									new BigDecimal[] { BigDecimal.ONE, (data[i][k].multiply(new BigDecimal("-1"))) });
							sb.append(temp_num.toString(i));
							numerator = numerator.multi(temp_num);

						}


					}
					sb.append(" * " + f_values[j]);
					sb.append("/" + denominator + (j >= data[i].length-1 ? "" :" + "));


					//numerator.multi(f_values[j]);
					num_terms.add(numerator);
					den_terms.add(denominator);
				}
			}
			System.out.println(sb.toString());
			sb = new StringBuilder();
			numerators.add(num_terms);
			denominators.add(den_terms);

		}

		int count = 0;
		for (int i : ind_selection) {
			BigDecimal result = BigDecimal.ZERO;
			System.out.println("\nx" + (i) + ":");
			for (int j : dep_selection) {
				for (Polynomial term : numerators.get(i)) {
					BigDecimal num = term.evaluate(data[i][j]).multiply(f_values[not_missing_indices.get(count)]);

					BigDecimal dem = denominators.get(i).get(count++);
					result = result.add(num.divide(dem, 4, RoundingMode.HALF_UP));

				}
				count = 0;

				System.out.println(result);
				result = BigDecimal.ZERO;
			}

		}
		System.out.println("\nGeneralized: ");

		for (int j : dep_selection) {
			BigDecimal result = BigDecimal.ZERO;
			// System.out.println("x" + (i+1) + ":\n");

			for (int k = 0; k < numerators.get(0).size(); k++) {
				BigDecimal num = BigDecimal.ONE;
				BigDecimal dem = BigDecimal.ONE;
				for (int i : ind_selection) {

					num = num.multiply(numerators.get(i).get(k).evaluate(data[i][j]));


					dem = dem.multiply(denominators.get(i).get(count));


				}

				num = num.multiply(f_values[not_missing_indices.get(count)]);

				result = result.add(num.divide(dem, 4, RoundingMode.HALF_UP));

				count++;

			}
			count = 0;

			System.out.println(result);

		}

		if(error_check  >0) {
			System.out.println("\nError Check: ");
			for (int i = 0, index = 0; i < f_values.length; i++) {
				if (f_values[i] != null) {
					BigDecimal temp = f_values[i];
					f_values[i] = null;
					new Lagrange(data,f_values, ind_selection, dep_selection, 0);
					f_values[i] = temp;
				}
			}
			
		}




	}


	class Polynomial {
		BigDecimal[] coefficients;
		int degree;
		int size;

		public Polynomial(int degree, BigDecimal[] coefficients) {
			this.coefficients = coefficients;
			this.degree = degree;
		}

		public Polynomial(int degree) {
			this.degree = degree;
			coefficients = new BigDecimal[1000];
			Arrays.fill(coefficients, BigDecimal.ZERO);
		}

		public Polynomial multi(Polynomial p) {
			Polynomial c = new Polynomial(degree + p.degree);

			for (int i = 0; i <= degree; i++) {
				for (int j = 0; j <= p.degree; j++) {
					c.coefficients[i + j] = c.coefficients[i + j].add(coefficients[i].multiply(p.coefficients[j]));
				}
			}

			return c;
		}

		public void multi(BigDecimal scalar) {
			for (int i = 0; i <= degree; i++) {
				coefficients[i] = coefficients[i].multiply(scalar);
			}
		}

		public BigDecimal evaluate(BigDecimal x) {
			BigDecimal result = BigDecimal.ZERO;
			for (int i = 0; i <= degree; i++) {
				// BigInteger power = new BigInteger(Integer.toString(degree-i));
				result = result.add(coefficients[i].multiply(x.pow(degree - i)));
				// result += (coefficients[i] * (Math.pow(x, degree - i)));
			}

			return result;
		}

		public void setDegree() {
			for (int i = 0; i <= coefficients.length; i++) {
				if (coefficients[i].compareTo(BigDecimal.ZERO) == 0) {
					degree = i - 1;
					break;
				}
			}
		}

		public int getDegree() {
			return degree;
		}

		public String toString(int index) {
			StringBuilder sb = new StringBuilder();
			sb.append("(");
			for (int i = 0; i <= degree; i++) {
				sb.append((coefficients[i] == BigDecimal.ONE ? "" : (coefficients[i])) + ((degree - i) == 0 ? "" : ("x" + (index) + "^" + (degree-i)))
						+ ((i != degree && (coefficients[i + 1].compareTo(BigDecimal.ZERO) == 1)) ? "+" : ""));
			}
			sb.append(")");
			return sb.toString();
		}
	}

}
