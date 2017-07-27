package orgs.cm.pMqp.pComms;

public class Test00 {

	public static void main(String[] args){
		String[] sub00 = "aa}}}ss}}}ddd".split("}}}");
		for(String d : sub00){
			System.out.println(":" + d + "|");
		}
		System.out.println("----------------------------------");
		String[] sub01 = "aa}}}ss}}}".split("}}}", -1);
		for(String d : sub01){
			System.out.println(":" + d + "|");
		}
		System.out.println("----------------------------------");
		String[] sub02 = "}}}ss}}}".split("}}}", -1);
		for(String d : sub02){
			System.out.println(":" + d + "|");
		}
		System.out.println("----------------------------------");
		String[] sub03 = "}}}}}}".split("}}}", -1);
		for(String d : sub03){
			System.out.println(":" + d + "|");
		}
		System.out.println("----------------------------------");
		String[] sub04 = "}}}}}}www".split("}}}", -1);
		for(String d : sub04){
			System.out.println(":" + d + "|");
		}
		System.out.println("----------------------------------");
		String[] sub05 = "}}}}}}www}}}sss".split("}}}", -1);
		for(String d : sub05){
			System.out.println(":" + d + "|");
		}
		System.out.println("----------------------------------");
		String[] sub06 = "".split("}}}", -1);
		for(String d : sub06){
			System.out.println(":" + d + "|");
		}
	}
}
