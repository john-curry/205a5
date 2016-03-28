public class BilinearFilter extends Filter {

  double Clamp(double x, double top, double bottom) {
    return Math.max(bottom, Math.min(top, x));
  }
  double IClamp(double x) {
    return Clamp(x, 255, 0);
  }
  boolean Outside(double x, double top, double bottom) {
    if (x >= bottom && x <= top) return false;
    return true;
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

    int new_width  = width * 3;
    int new_height = height * 3;

    double xc = (double)new_width / 2.0;
    double yc = (double)new_height / 2.0;

    double rmax = 100;
    double alpha = 30;

    int[][] out_image = new int[new_width][new_height];

    for (int i = 0; i < new_width; i++) {
      for (int j = 0; j < new_height; j++) {
        double dx = i - xc;
        double dy = j - yc;
        double radius = Math.sqrt(dx*dx + dy*dy);
        double beta = Math.atan2(j, i) + (alpha * ((rmax - radius) / rmax));

        double x = radius > rmax ? i : xc + radius * Math.cos(beta);
        double y = radius > rmax ? j : yc + radius * Math.sin(beta);

        x = x * (double)width  / (double)new_width;
        y = y * (double)height / (double)new_height;

        if (Outside(x, width - 1, 0) || Outside(y, height - 1, 0)) out_image[i][j] = 128;
        else out_image[i][j] = (int)Bilinear(image, x, y); //image[(int)x][(int)y];
      }
    }
    return out_image;
  }
}
