public class BilinearFilter extends Filter {

  double Bilinear(int[][] image, double x, double y) {
    int width = image.length;
    int height = image[0].length;

    double x0 = Math.min(width  - 1, Math.floor(x));
    double y0 = Math.min(height - 1, Math.floor(y));
    double x1 = Math.min(width  - 1, Math.ceil(x));
    double y1 = Math.min(height - 1, Math.ceil(y));
    double xs = Math.min(width  - 1, x - x0);
    double ys = Math.min(height - 1, y - y0);
    double p0 = image[(int)x0][(int)y0]*(1 - xs) + image[(int)x1][(int)y0] * xs;
    double p1 = image[(int)x0][(int)y1]*(1 - xs) + image[(int)x1][(int)y1] * xs;
    return p0*(1 - ys) + p1*ys;
  }

  int [][] ApplyFilter(int[][] image) {
    int width = image.length;
    int height = image[0].length;

    int new_width  = width * 3;
    int new_height = height * 3;

    int[][] out_image = new int [new_width][new_height];

    for (int i = 0; i < new_width; i++) {
      for (int j = 0; j < new_height; j++) {
        double x = i * (double)width / (double)new_width;
        double y = j * (double)height / (double)new_height;
        out_image[i][j] = Math.max(0, Math.min(255, (int)Bilinear(image, x, y)));
      }
    }

    return out_image;
  }
}
