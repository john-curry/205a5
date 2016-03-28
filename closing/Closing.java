public class Closing extends Filter {
  boolean[][] Erosion(boolean[][] bin, boolean[][] filter) {
    int center = 2;
    int width = bin.length;
    int height = bin[0].length;
    int h_width = filter.length;
    int h_height = filter[0].length;

    boolean[][] bout = new boolean[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        // if any if the pixels in h are true the set the pixel to true
        boolean is_black = true;
        for (int x = 0; x < h_width; x++) {
          for (int y = 0; y < h_height; y++) {
            int target_x = i + x - center;
            int target_y = j + y - center;
            if (target_x < 0) target_x = width  + target_x;
            if (target_y < 0) target_y = height + target_y;
            if (target_x >= width)  target_x = target_x - width;
            if (target_y >= height) target_y = target_y - height;

            if (!(bin[target_x][target_y] || filter[x][y])) {
              is_black = false;
            }
          }
        }
        if (is_black) {
          bout[i][j] = true;
        } else {
          bout[i][j] = false;
        }
      }
    }
    return bout;
  }

  boolean[][] Dilation(boolean[][] bin, boolean[][] filter) {
    int center = 2;
    int width = bin.length;
    int height = bin[0].length;
    int h_width = filter.length;
    int h_height = filter[0].length;
    boolean[][] bout = new boolean[width][height];

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        // if any if the pixels in h are true the set the pixel to true
        boolean is_black = false;
        for (int x = 0; x < h_width; x++) {
          for (int y = 0; y < h_height; y++) {
            int target_x = i + x - center;
            int target_y = j + y - center;
            if (target_x < 0) target_x = width  + target_x;
            if (target_y < 0) target_y = height + target_y;
            if (target_x >= width)  target_x = target_x - width;
            if (target_y >= height) target_y = target_y - height;

            if (bin[target_x][target_y] && filter[x][y]) {
              is_black = true;
            }
          }
        }
        if (is_black) {
          bout[i][j] = true;
        } else {
          bout[i][j] = false;
        }
      }
    }
    return bout;
  }

  int [][] ApplyFilter(int[][] image) {
    int width = image.length;
    int height = image[0].length;

    int[][] out_image = new int    [width][height];
    boolean[][] bin   = new boolean[width][height];
    boolean[][] bout  = new boolean[width][height];


    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if (image[i][j] < 128) {
          bin[i][j] =  true;
        }
      }
    }

    boolean[][] h = 
      { 
        { false , false, true, false, false },
        { false ,  true, true,  true, false },
        {  true ,  true, true,  true,  true },
        { false ,  true, true,  true, false },
        { false , false, true, false, false }
      };
    
    bout = Dilation(bin, h);
    bout = Erosion(bin, h);

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if (bout[i][j]) {
          out_image[i][j] = 0;
        } else {
          out_image[i][j] = 255;
        }
      }
    }
    return out_image;
  }
}
