public class Out {
  public static void put(String s) {
    System.out.println(s);
  }

  //public static void put(Object o) {
  //  System.out.println(o.toString());
  //}

  public static void put(int[] h) {
    for (int i = 0; i < h.length; i++) {
        System.out.print(h[i] + " ");
    }
    System.out.println();
  }

  public static void put(boolean[][] h) {
    for (int i = 0; i < h.length; i++) {
      for (int j = 0; j < h[0].length; j++) {
        if (h[i][j]) { 
          System.out.print(1 + " ");
        } else {
          System.out.print(0 + " ");
        }
      }
      System.out.println();
    }
    System.out.println();
  }

  public static void put(double[] h) {
    for (int i = 0; i < h.length; i++) {
        System.out.print(h[i] + " ");
    }
    System.out.println();
  }
}
