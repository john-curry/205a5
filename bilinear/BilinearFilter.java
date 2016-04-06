public class BilinearFilter extends Filter {

  double width_scale;
  double height_scale;

  public BilinearFilter(double width, double height) {
    this.width_scale = width; this.height_scale = height;
  }

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

    double new_width  = width  * width_scale;
    double new_height = height * height_scale;

    Out.put("width: " +  new_width);
    Out.put("height: " + new_height);

    int[][] out_image = new int [(int)new_width][(int)new_height];

    for (int i = 0; i < new_width; i++) {
      for (int j = 0; j < new_height; j++) {
        double x = i * (double)width  / (double)new_width;
        double y = j * (double)height / (double)new_height;
        out_image[i][j] = Math.max(0, Math.min(255, (int)Bilinear(image, x, y)));
      }
    }

    return out_image;
  }
}
