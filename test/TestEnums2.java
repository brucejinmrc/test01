package test;

 

public class TestEnums2 {

	public static final void main(String... aArgs) {
 		log(Quark.UP);
 		String up = "UP";
 		 
		log("Done.");
	}

	// PRIVATE //
	private static void log(Object aText) {
		System.out.println(String.valueOf(aText));
	}

	/** Example 1 - simple list of enum constants. */
	 enum Quark {
		/*
		 * These are called "enum constants". An enum type has no instances
		 * other than those defined by its enum constants. They are implicitly
		 * "public static final". Each enum constant corresponds to a call to a
		 * constructor. When no args follow an enum constant, then the
		 * no-argument constructor is used to create the corresponding object.
		 */
		UP, DOWN, CHARM, STRANGE, BOTTOM, TOP
	}

	 
}
