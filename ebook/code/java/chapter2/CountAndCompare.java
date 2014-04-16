class CountAndCompare implements ICompare {
    
    private static String a = "ABCDMNOPQRSABCDEFGHL";
	
	private static String b = "DCGSRQPOt";
	
	private static char[] aChar;
	
	private static char[] bChar;
	
	static{
		aChar = a.toUpperCase().toCharArray();
		bChar = b.toUpperCase().toCharArray();
	}
	
	
    @Override
    public boolean compare(String longString, String shortString) {
        int posLong = 0, posShort = 0;
        longString = countSort(longString);
        shortString = countSort(shortString);
        while (posLong < longString.length() && posShort < shortString.length()) {
            while (posLong < longString.length() && longString.charAt(posLong) < shortString.charAt(posShort)) {
                posLong++;
            }

            if (posLong >= longString.length() || longString.charAt(posLong) != shortString.charAt(posShort)) {
                return false;
            } else {
                posShort++;
            }
        }
        return posShort == shortString.length();
    }

    private String countSort(String str) {
        int[] help = new int[26];
        for (int i = 0; i < str.length(); i++) {
            int index = str.charAt(i) - 'A';
            help[index]++;
        }
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < 26; i++) {
           for (int j = 0; j < help[i]; j++) {
               buf.append((char)(i + (int)'A'));
           } 
        }
        return buf.toString();
    }
    
    //牛逼大招 用素数方案！
    public static boolean isExistsBySushu(){
		int p[] = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59,61, 67, 71, 73, 79, 83, 89, 97, 101};
		BigInteger count = new BigInteger("1");
		
		for(int i=0;i<aChar.length;i++){
			BigInteger temp = new BigInteger(String.valueOf(p[aChar[i]-'A']));
			count = count.multiply(temp);
		}
		System.out.println(count);
		for(int i=0;i<bChar.length;i++){
			BigInteger temp = new BigInteger(String.valueOf(p[bChar[i]-'A']));
			if(count.remainder(temp).equals("0")){
				return false;
			}
		}
		return true;
	}
	 
	public static void main(String args[]){
		System.out.println(isExistsBySushu());
	}


}
