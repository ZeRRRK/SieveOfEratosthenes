/**
 * An implementation of the Sieve of Eratosthenes.
 * Information can be found here:
 * 
 * <http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes>
 *
 * Copyright (C) 2013 Matt Yackel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * 
 * @author Matt
 *
 */
public class Sieve {

	/*
	 * An array of flags, indicating whether or not the given value is prime.
	 */
	private boolean[] prime;
	
	/*
	 * Sieve evaluated flag.
	 */
	private boolean evaluated = false;
	
	/*
	 * An array of all prime numbers from p (2) to n (given range).
	 */
	private int[] sieve;
	
	/**
	 * Creates a new instance of this class.
	 * @param n The range in which to check for prime numbers.
	 */
	public Sieve(int n) {
		if(n < 2) {
			throw new IllegalArgumentException("Value n must be greater than 1.");
		}
		prime = new boolean[n + 1];
		for(int i = 0; i < n + 1; i++) {
			prime[i] = true;
		}
		prime[0] = false;
		prime[1] = false;
	}
	
	/**
	 * Evaluate the sieve.
	 */
	public void evaluate() {
		if(evaluated) {
			throw new IllegalStateException("Sieve has already been evaluated.");
		}
		for(int i = 2; i * i < prime.length; i++) {
			if(prime[i]) {
				for(int i2 = i * i; i2 < prime.length; i2 += i) {
					prime[i2] = false;
				}
			}
		}
		int length = 0;
		for(int i = 0; i < prime.length; i++) {
			if(prime[i]) {
				length++;
			}
		}
		sieve = new int[length];
		int index = 0;
		for(int i = 0; i < prime.length; i++) {
			if(prime[i]) {
				sieve[index++] = i;
			}
		}
		evaluated = true;
	}
	
	/**
	 * Checks whether or not the given number is prime.
	 * @param i The value.
	 * @return <code>True</code> if the value is prime, <code>False</code> if not.
	 */
	public boolean isPrime(int i) {
		if(!evaluated) {
			throw new IllegalStateException("Sieve has not yet been evaluated.");
		} else if(i < 2) {
			throw new IllegalArgumentException("Value i must be greater than 1.");
		} else if(i > prime.length) {
			throw new IllegalArgumentException("Value i exceeds sieve range.");
		}
		return prime[i];
	}
	
	/**
	 * Gets the value of the nth prime.
	 * @param n The index.
	 * @return The value.
	 */
	public int nthPrime(int n) {
		if(!evaluated) {
			throw new IllegalStateException("Sieve has not yet been evaluated.");
		} else if(n < 1) {
			throw new IllegalArgumentException("Value n must be greater than 0.");
		} else if(n > sieve.length) {
			throw new IllegalArgumentException("Value n exceeds sieve range.");
		}
		return sieve[n - 1];
	}
	
	/**
	 * Gets the number of prime numbers between p and n.
	 * @return The number of primes.
	 */
	public int nPrimes() {
		if(!evaluated) {
			throw new IllegalStateException("Sieve has not yet been evaluated!");
		}
		return sieve.length + 1;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[");
		for(int i = 0; i < sieve.length; i++) {
			str.append(sieve[i]);
			if(i != sieve.length - 1) {
				str.append(", ");
			}
		}
		str.append("]");
		return str.toString();
	}
	
}
