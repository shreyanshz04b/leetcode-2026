class Robot {

    int w, h, x, y, d, cycle;

    public Robot(int width, int height) {
        w = width;
        h = height;
        x = 0;
        y = 0;
        d = 0;
        cycle = 2 * (w + h) - 4;
    }
    
    public void step(int num) {
        if (cycle == 0) return;

        num %= cycle;

        if (num == 0) {
            if (x == 0 && y == 0) d = 3;
            return;
        }

        while (num > 0) {
            if (d == 0) {
                int k = Math.min(num, w - 1 - x);
                x += k;
                num -= k;
                if (num > 0) d = 1;
            } else if (d == 1) {
                int k = Math.min(num, h - 1 - y);
                y += k;
                num -= k;
                if (num > 0) d = 2;
            } else if (d == 2) {
                int k = Math.min(num, x);
                x -= k;
                num -= k;
                if (num > 0) d = 3;
            } else {
                int k = Math.min(num, y);
                y -= k;
                num -= k;
                if (num > 0) d = 0;
            }
        }
    }
    
    public int[] getPos() {
        return new int[]{x, y};
    }
    
    public String getDir() {
        return d == 0 ? "East" : d == 1 ? "North" : d == 2 ? "West" : "South";
    }
}
